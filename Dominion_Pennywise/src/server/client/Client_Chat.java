package server.client;

import java.io.IOException;
import java.net.Socket;

import Splash.Server_View;
import commons.ChatMsg;
import commons.JoinMsg;
import commons.Message;
import commons.StringMsg;
import javafx.concurrent.Task;

public class Client_Chat {
	private Socket socket;
	private String playerName;
	Server server;
	Server_View serverView;
	ServerHandler sh;
	
	protected Client_Chat(Server server,Socket socket) {
		this.server = server;
		this.socket = socket;
		sh = new ServerHandler(); 
		new Thread(messageThread).start();
		
	}

	final Task<Void> messageThread = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
			while(true) {
			Message msg = Message.receive(socket);
			if (msg instanceof ChatMsg) {
				server.broadcast((ChatMsg) msg);
			} else if (msg instanceof JoinMsg) {
				Client_Chat.this.playerName = ((JoinMsg) msg).getPlayerName();
				System.out.println("joinmsg");
			} else if (msg instanceof StringMsg) {
				//serverView.updateServerView(server.getNewestMsg(), ((StringMsg) msg).getContent());
//				System.out.println(((StringMsg) msg).getContent() + " has joined the Server!");
				String message = ((StringMsg) msg).getContent();
				if(message.substring(0, 5).equals("lobby")) {
					playerName = message.substring(5);
					
				}else {
					server.sendToClient(message);
				}
				sh.getMessageFromServer(((StringMsg) msg).getContent());	
			}
			}
		}
	};

	public void sendChatMsg(ChatMsg msg) {
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
