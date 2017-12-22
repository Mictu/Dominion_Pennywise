package server.client;

import java.io.IOException;
import java.net.Socket;
import commons.ChatMsg;
import commons.CloseMsg;
import commons.Message;
import commons.StringMsg;
import javafx.concurrent.Task;
import server_Models.Player;

/**
 * Client_Chat class a helping class for server and handles receiving messages
 * for the server.
 * 
 * @author Michael Tu
 *
 * @Source pattern for communication is taken from ChatLab SoftwareEngineering2
 */
public class Client_Chat {
	private Socket socket;
	private String playerName;
	Server server;
	ServerHandler sh;
	Player player;
	String playerNameList = "lobby.";
	private volatile boolean close;

	/**
	 * Thread messageThread and ServerHandler will start after Client_Chat is
	 * initialized.
	 * 
	 * @param server
	 * @param socket
	 * 
	 * @author Michael Tu
	 */
	public Client_Chat(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		sh = new ServerHandler(server);
		new Thread(messageThread).start();
	}

	/**
	 * Thread handling incoming/outgoing messages for/from server.
	 * 
	 * @author Michael Tu
	 * @Source ChatLab SoftwareEngineering2
	 */
	final Task<Void> messageThread = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			while (!close) {
				try {
					Message msg = Message.receive(socket);
					if (msg instanceof ChatMsg) {
						server.broadcast((ChatMsg) msg); // broadcast chat messages to every connected client
					} else if (msg instanceof StringMsg) {
						String message = ((StringMsg) msg).getContent(); // handles String messages
						if (message.length() > 4 && message.substring(0, 5).equals("lobby")) { // for playername
							playerName = message.substring(5);
							if (Player.player.size() < 4) {
								sh.addPlayerToList(playerName); // initialize new Player with playername
								for (Player p : Player.player) {
									String pname = p.getName();
									playerNameList = playerNameList.concat(pname + ".");
									System.out.println(playerNameList);
								}
								server.sendToClient(playerNameList); // send list to client to update in Lobby
							}
						} else {
							sh.getMessageFromServer(message);
						}
					} else if (msg instanceof CloseMsg) { // close message send to client(socket) and close this socket
						sendCloseMsg((CloseMsg) msg);
						int index = 0;
						String name = ((CloseMsg) msg).getPlayerName();
						boolean sameName = false;
						while (!sameName) {
							if (Player.player.get(index).getName().equals(name)) { // also remove the player who closes
								server.clients.remove(index);
								Player.player.remove(index);
								sameName = true;
								index++;
							}
						}
						if (!(Player.player.isEmpty())) { // send new playerlist without the leaving player
							playerNameList = "lobby.";
							for (Player p : Player.player) {
								String pname = p.getName();
								playerNameList = playerNameList.concat(pname + ".");
							}
							server.sendToClient(playerNameList);
						}
						socket.close();
						close = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Verbindung fehlgeschlagen");
				}
			}
			return null;
		}
	};

	/**
	 * send chat message
	 * 
	 * @param msg
	 * @Source ChatLab SoftwareEngineering 2
	 * @author Brad Richards
	 */
	public void sendChatMsg(ChatMsg msg) {
		msg.send(socket);
	}

	/**
	 * send close message
	 * 
	 * @param msg
	 * @Source ChatLab SoftwareEngineering 2
	 * @author Brad Richards
	 * @author Michael Tu
	 */
	public void sendCloseMsg(CloseMsg msg) {
		msg.send(socket);
	}

	/**
	 * send String message to client
	 * @param msg
	 * @author Brad Richards
	 * @author Michael Tu
	 */
	public void sendStringMsgToClient(String msg) {
		Message message = new StringMsg(playerName, msg);
		message.send(socket);
	}

	public String getPlayername() {
		return playerName;
	}

	public void stop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
