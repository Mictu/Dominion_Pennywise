package server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import commons.ChatMsg;
import commons.JoinMsg;
import commons.Message;
import commons.StringMsg;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;

import java.util.ArrayList;
import controllers.ClientHandler;


public class Client {

	Socket socket;

	DataOutputStream output;
	DataInputStream input;
	String info;
	Server server;

	ClientHandler clientH;
	ArrayList<String> deck = new ArrayList<String>();


	public SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected String playerName;

	public Client(String playerName) {
		this.playerName = playerName;
	}

	public void run() {
		new Thread(startClient).start();
	}

	final Task<Void> startClient= new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			try {
				socket = new Socket("localhost", 2303);
				System.out.println("Player " + playerName + " is connected");
				sendToServer(playerName);
				// Chat
				while(true) {
				ChatMsg chatMsg = (ChatMsg) Message.receive(socket);
				Platform.runLater(()-> {
					newestMessage.set(chatMsg.getPlayerName() + ": " + chatMsg.getContent());
				});
				}
				

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbindung fehlgeschlagen");
			} finally {
				socket.close();
				System.out.println("Client closed");

			}
			Message jmsgForServer = new JoinMsg(playerName);
			jmsgForServer.send(socket);
			return null;
		}

	};

	public void sendToServer(String msg) {
		Message stringMsg = new StringMsg(msg);
		System.out.println(stringMsg);
		stringMsg.send(socket);
//		try {
//			output.writeUTF(msg);
//			System.out.println("Client sendet zum Server: " + msg);
//			output.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void getStringFromServer() {
		try {
			System.out.println(input.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Chat methods

	public void sendChatMessage(String message) {
		Message msg = new ChatMsg(playerName, message);
		msg.send(socket);
	}

	public String receiveMessage() {
		return newestMessage.get();
	}

	public void disconnectClient() {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String getPlayerName() {
		return playerName;
	}

	@SuppressWarnings("unchecked")
	public void getArrayFromServer() {
		try {
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
			
			clientH = new ClientHandler();
			
			this.deck = (ArrayList<String>) objectInput.readObject();
			
//			clientH.getDeckFromServer(deck);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
