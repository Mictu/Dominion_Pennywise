package server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import Splash.Server_View;
import commons.ChatMsg;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;

/**
 * Server class is in the same time the main server class.
 * 
 * @author Michael Tu
 * @author Brad Richards
 */
public class Server extends Application {

	public final ObservableList<Client_Chat> clients = FXCollections.observableArrayList();

	ServerSocket server;
	String ip;
	ArrayList<String> fromServer;
	private volatile boolean stop = false;
	Server_View serverView;

	protected SimpleStringProperty newestMsg = new SimpleStringProperty();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		serverView = new Server_View(primaryStage);
		serverView.withServer.setOnAction(event -> {
			serverView.withServer.setDisable(true);
			startServer();
		});
		serverView.start();

		newestMsg.addListener((o, oldvalue, newValue) -> serverView.txtClientArea.appendText(newValue + "\n"));
		primaryStage.setOnCloseRequest(event -> stopServer());
	}

	public void stopServer() {
		for (Client_Chat c : clients)
			c.stop();
		stop = true;
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				// Uninteresting
			}
		}
	}

	/**
	 * Method to start the server in a thread.
	 * @author Michael Tu
	 */
	public void startServer() {
		new Thread(startServer).start();
	}

	/**
	 * Task for starting server and listens for a connection to be made to this socket and accepts it.
	 * 
	 * @author Michael Tu, Brad Richards
	 * @Source pattern taken from ChatLab SoftwareEngineering2
	 */
	final Task<Void> startServer = new Task<Void>() {
		@Override
		protected Void call() throws Exception {

			getExternalIp();
			server = new ServerSocket(2303, 4);
			serverView.updateServerView(newestMsg, "Waiting for Connection...");

			InetAddress iAddress = InetAddress.getLocalHost();
			serverView.updateServerView(newestMsg, "Server IP address : " + iAddress.getHostAddress());
			while (!stop) {
				Socket socket = server.accept();
				if (clients.size() < 4) { //at the most 4 players are able to connect to server
					Client_Chat clientC = new Client_Chat(Server.this, socket);
					clients.add(clientC);
				}
				serverView.updateServerView(newestMsg,
						"Connection received from: " + socket.getInetAddress().getHostName());
			}
			return null;
		}

	};

	/**
	 * Method to send a string message to every connected client
	 * 
	 * @param msg
	 * 
	 * @Source pattern taken from ChatLab SoftwareEngineering2
	 * 
	 * @Michael Tu
	 * @author Brad Richards
	 * 
	 */
	public void sendToClient(String msg) {
		for (Client_Chat c : clients) {
			c.sendStringMsgToClient(msg);
		}
	}

	/**
	 * send String to specific client via index
	 * 
	 * @param msg
	 * @param index
	 * 
	 * @author Michael Tu
	 * @Source pattern taken from ChatLab SoftwareEngineering2
	 */
	public void sendStringToClient(String msg, int index) {
		clients.get(index).sendStringMsgToClient(msg);
	}

	/**
	 * Broadcast chat message to every connected client
	 * 
	 * @param outMss
	 * @Source ChatLab SoftwareEngineering 2
	 * @author Brad Richards
	 */
	public void broadcast(ChatMsg outMsg) {
		for (Client_Chat c : clients) {
			c.sendChatMsg(outMsg);
		}
	}

	/**
	 * Get the external IP here to be able to play online (over different networks)
	 * 
	 * @return ip
	 * @Source https://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java
	 */
	public String getExternalIp() {
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine(); // get the IP as a String (send to website and
								// get answer with ip)
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("type this into your client to play online: " + ip);
		return ip;
	}

}
