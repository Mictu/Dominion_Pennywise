package server.client;

import java.net.Socket;

import commons.ChatMsg;
import commons.CloseMsg;
import commons.Message;
import commons.StringMsg;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import controllers.ClientHandler;

/**
 * Client Class handles the communication on client side.
 * 
 * @author Michael Tu
 *
 */
public class Client {

	Socket socket;
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

	public SimpleStringProperty newestMessage = new SimpleStringProperty();
	protected String playerName;
	private volatile boolean close = false;

	/**
	 * Client Constructor contains playerName.
	 * 
	 * @param playerName
	 * 
	 * @author Michael Tu
	 * @author Brad Richards
	 * 
	 * @Source Class pattern taken from ChatLab SoftwareEngineering 2
	 */
	public Client(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * starts thread with startClient task
	 * 
	 * @author Michael Tu
	 */
	public void run() {
		new Thread(startClient).start();
	}

	/**
	 * Starts client to connect to server. If client is connected to the server, the
	 * client will send its playerName to the server. It also receives messages and
	 * check which type it is and act accordingly.
	 * 
	 * 
	 * @author Michael Tu
	 * @author Brad Richards
	 * @Source ChatLab SoftwareEngineering2
	 */
	final Task<Void> startClient = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			try {

				socket = new Socket("localhost", 2303);
				System.out.println("Player " + playerName + " is connected");
				sendToServer("lobby" + playerName);

				// Chat
				while (!close) {
					Message msg = Message.receive(socket); //receiving messages
					if (msg instanceof ChatMsg) { // for chat message it will set this message to newestMessage
						ChatMsg chatMsg = (ChatMsg) msg;
						Platform.runLater(() -> {
							newestMessage.set(chatMsg.getPlayerName() + ": " + chatMsg.getContent());
						});
					} else if (msg instanceof CloseMsg) { // for close message it will close the socket
						socket.close();
						close = true;
					} else if (msg instanceof StringMsg) {
						String message = ((StringMsg) msg).getContent();
						if (message.length() > 4 && message.substring(0, 5).equals("lobby")) {
							namemsg = message.substring(6);
							names = namemsg.split("\\.");
							Platform.runLater(() -> {
								ClientHandler.getNamesFormClient(names);
							});
						} else if (message.length() > 8 && message.substring(0, 9).equals("abmpoints")) {
							abmPoints = message.substring(10);
							abm = abmPoints.split("\\.");
							ch.getABMpoints(abm);
						} else if (message.length() > 6 && message.substring(0, 6).equals("rounds")) {
							ch.showRoundCounter(message.substring(6));
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
							results = message.substring(7);
							resultsPlayerAndPoints = results.split("\\.");
							ch.sendResultPointsToDisplay(resultsPlayerAndPoints);
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
			}
			return null;
		}
	};

	/**
	 * send String messsage to server
	 * 
	 * @param msg
	 * 
	 * @author Michael Tu
	 * 
	 * @Source pattern taken form ChatLab SoftwareEngineering2
	 */
	public void sendToServer(String msg) {
		Message stringMsg = new StringMsg(playerName, msg);
		stringMsg.send(socket);
	}

	/**
	 * send chat message to server
	 * 
	 * @param message
	 * 
	 * @author Brad Richards
	 */
	public void sendChatMessage(String message) {
		Message msg = new ChatMsg(playerName, message);
		msg.send(socket);
	}

	/**
	 * send close message to server
	 * 
	 * @param message
	 * 
	 * @author Michael Tu
	 * @author Brad Richards
	 * 
	 * @Source pattern taken from ChatLab SoftwareEngineering2
	 */
	public void sendCloseMessage(String message) {
		Message msg = new CloseMsg(playerName, message);
		msg.send(socket);

	}

	/**
	 * method to send "close" message, consists sendCloseMessage();
	 * @author Michael Tu
	 */
	public void disconnectClient() {
		sendCloseMessage("close");
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getNames() {
		return names;
	}
}
