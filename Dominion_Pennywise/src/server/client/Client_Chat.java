package server.client;

import java.io.IOException;
import java.net.Socket;
import Splash.Server_View;
import commons.ChatMsg;
import commons.CloseMsg;
import commons.JoinMsg;
import commons.Message;
import commons.StringMsg;
import javafx.concurrent.Task;
import server_Models.Player;

public class Client_Chat {
	private Socket socket;
	private String playerName;
	Server server;
	Server_View serverView;
	ServerHandler sh;
	Player player;
	String playerNameList = "lobby.";
	private volatile boolean close;

	public Client_Chat(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		sh = new ServerHandler(server);
		new Thread(messageThread).start();
	}

	final Task<Void> messageThread = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			while (!close) {
				try {
					Message msg = Message.receive(socket);
					if (msg instanceof ChatMsg) {
						server.broadcast((ChatMsg) msg);
					} else if (msg instanceof JoinMsg) {
						Client_Chat.this.playerName = ((JoinMsg) msg).getPlayerName();
					} else if (msg instanceof StringMsg) {
						String message = ((StringMsg) msg).getContent();
						if (message.length() > 4 && message.substring(0, 5).equals("lobby")) {
							playerName = message.substring(5);
							if (Player.player.size() < 4) {
									sh.addPlayerToList(playerName);
								for (Player p : Player.player) {
									String pname = p.getName();
									playerNameList = playerNameList.concat(pname + ".");
									System.out.println(playerNameList);
								}
								server.sendToClient(playerNameList);
							}
						} else {
							sh.getMessageFromServer(message);
						}
					} else if (msg instanceof CloseMsg) {
						sendCloseMsg((CloseMsg) msg);
						int index = 0;
						String name = ((CloseMsg) msg).getPlayerName();
						boolean sameName = false;
						while (!sameName) {
							if (Player.player.get(index).getName().equals(name)) {
								server.clients.remove(index);
								Player.player.remove(index);
								sameName = true;
								index++;
							}
						}
						if (!(Player.player.isEmpty())) {
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

	public void sendChatMsg(ChatMsg msg) {
		msg.send(socket);
	}

	public void sendCloseMsg(CloseMsg msg) {
		msg.send(socket);
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
