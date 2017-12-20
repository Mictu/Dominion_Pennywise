package server.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Splash.Server_View;
import commons.ChatMsg;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import server_Models.Player;

public class Server extends Application {

	public final ObservableList<Client_Chat> clients = FXCollections.observableArrayList();

	ServerSocket server;

	Player player;
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
			connect();
		});
		serverView.start();
		
		newestMsg.addListener((o, oldvalue, newValue) -> serverView.txtClientArea.appendText(newValue + "\n"));
//		clients.addListener((ListChangeListener) (event -> serverView.updateClients()));
		primaryStage.setOnCloseRequest(event -> stopServer());
	}

	
	public void stopServer() {
		for (Client_Chat c : clients) c.stop();
		stop = true;
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				// Uninteresting
			}
		}
	}

	public void connect() {
		new Thread(startServer).start();
	}

	final Task<Void> startServer = new Task<Void>() {
		@Override
		protected Void call() throws Exception {

			server = new ServerSocket(2303, 10);
			// System.out.println("Waiting for Connection");
			serverView.updateServerView(newestMsg, "Waiting for Connection...");

			InetAddress iAddress = InetAddress.getLocalHost();
			// System.out.println("Server IP address : " + iAddress.getHostAddress());
			serverView.updateServerView(newestMsg, "Server IP address : " + iAddress.getHostAddress());
			while(!stop) {
			Socket socket = server.accept();

			
			Client_Chat clientC = new Client_Chat(Server.this, socket);
			clients.add(clientC);

//			serverView.updateServerView(newestMsg, "Connection received from: " );
			serverView.updateServerView(newestMsg,
					"Connection received from: " + socket.getInetAddress().getHostName());
			}
			return null;
		}

	};


	public void setMessage(ArrayList<String> message) {
		this.fromServer = message;
	}
	
	public void sendToClient(String msg) {
		for(Client_Chat c : clients) {
			c.sendStringMsgToClient(msg);
		}
	}
	
	public void sendStringToClient(String msg, int index) {
		clients.get(index).sendStringMsgToClient(msg);
	}

	// chat

	public void broadcast(ChatMsg outMsg) {
		for (Client_Chat c : clients) {
			c.sendChatMsg(outMsg);
		}
	}

	public SimpleStringProperty getNewestMsg() {
		return newestMsg;
	}

}
