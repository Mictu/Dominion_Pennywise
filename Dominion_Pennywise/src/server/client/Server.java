package server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Splash.Server_View;
import commons.ChatMsg;
import commons.Message;
import commons.StringMsg;
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
	DataInputStream input;
	DataOutputStream output;

//	GameLogic gl = new GameLogic(); // Just for testing


	Player player;
	ArrayList<String> fromServer;

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

	private void stopServer() {
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			while(true) {
			Socket socket = server.accept();

			Client_Chat clientC = new Client_Chat(Server.this, socket);
//			System.out.println(client.toString());
			clients.add(clientC);
			
//			newestMsg.concat(clientC);
//			serverView.updateServerView(newestMsg, "Connection received from: " );
			serverView.updateServerView(newestMsg,
					"Connection received from: " + socket.getInetAddress().getHostName());
			
			}
		}

	};

	
//	public void serverReceivesMessages() {
//		String msg;
//		try {
//			msg = input.readUTF();
//			System.out.println("Server receives: " + msg);
//			sh.printOutFromServer(msg);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//	}

	public void setMessage(ArrayList<String> message) {
		this.fromServer = message;
	}
	
	public void sendToClient(String msg) {
		Message strMsg = new StringMsg(msg);
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
