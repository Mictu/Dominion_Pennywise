package server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import commons.ChatMsg;
import commons.CloseMsg;
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
	String namemsg;
	String abmPoints;
	String[] abm;
	String winPoints;
	String[] names;
	String loggerMessage;
	String[] win;
	String results;
	String[] resultsPlayerAndPoints;

	ClientHandler ch = new ClientHandler();

	ArrayList<String> deck = new ArrayList<String>();

	public SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected String playerName;
	private volatile boolean close = false;

	public Client(String playerName) {
		this.playerName = playerName;
	}

	public void run() {
		new Thread(startClient).start();
	}

	final Task<Void> startClient = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			try {

				socket = new Socket("localhost", 2303);
				System.out.println("Player " + playerName + " is connected");
				sendToServer("lobby" + playerName);

				// Chat
				while (!close) {
					Message msg = Message.receive(socket);
					if (msg instanceof ChatMsg) {
						ChatMsg chatMsg = (ChatMsg) msg;
						Platform.runLater(() -> {
							newestMessage.set(chatMsg.getPlayerName() + ": " + chatMsg.getContent());
						});
					} else if(msg instanceof CloseMsg) {
						socket.close();
						close = true;
					}else if (msg instanceof StringMsg) {
						String message = ((StringMsg) msg).getContent();
						if (message.length() > 4 && message.substring(0, 5).equals("lobby")) {
							namemsg = message.substring(6);
							names = namemsg.split("\\.");
							Platform.runLater(() -> {
								// for (String s : names) {
								// s = s.concat(" Online");
								// }
								// setNames(names);
								ClientHandler.getNamesFormClient(names);
							});
						} else if (message.length() > 8 && message.substring(0, 9).equals("abmpoints")) {
							abmPoints = message.substring(10);
							abm = abmPoints.split("\\.");
							ch.getABMpoints(abm);
						} else if (message.length() > 6 && message.substring(0, 6).equals("logger")) {
							loggerMessage = message.substring(6);
							ch.HandleLoggerMsg(loggerMessage);
						} else if (message.length() > 4 && message.substring(0, 4).equals("info")) {
							loggerMessage = message.substring(4);
							ch.HandleInfoMessage(loggerMessage);
						} else if (message.length() > 8 && message.substring(0, 9).equals("winpoints")) {
							winPoints = message.substring(10);
							win = winPoints.split("\\.");
							ch.getWinPoints(win);
						} else if (message.length() > 7 && message.substring(0, 7).equals("amount.")) {
							ch.HandleCardAmount(message.substring(7));
						} else if (message.length() > 6 && message.substring(0, 6).equals("result")) {
							if (message.length() > 17)
								results = message.substring(17); // message ohne result.winpoints.
							System.out.println(results);
							resultsPlayerAndPoints = results.split("\\.");
							ClientHandler.getResultPoints(resultsPlayerAndPoints);
						} else if (message.length() > 9 && message.substring(0, 9).equals("cardempty")) {
							ch.getMessageFromClient(message);
						} else {
							Platform.runLater(() -> {
								ch.getMessageFromClient(message);
							});
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Verbindung fehlgeschlagen");
				// } finally {
				// socket.close();
				// System.out.println("Client closed");
			}
//			Message jmsgForServer = new JoinMsg(playerName);
//			jmsgForServer.send(socket);
			return null;
		}

	};

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getNames() {
		return names;
	}

	public void sendToServer(String msg) {
		Message stringMsg = new StringMsg(playerName, msg);
		stringMsg.send(socket);
	}

	public void sendChatMessage(String message) {
		Message msg = new ChatMsg(playerName, message);
		msg.send(socket);
	}

	public void sendCloseMessage(String message) {
		Message msg = new CloseMsg(playerName, message);
		msg.send(socket);

	}

	public String receiveMessage() {
		return newestMessage.get();
	}

	public void disconnectClient(){
		sendCloseMessage("close");
	}

	public String getPlayerName() {
		return playerName;
	}
}
