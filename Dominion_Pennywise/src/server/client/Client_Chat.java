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
	
	protected Client_Chat(Server server,Socket socket) {
		this.server = server;
		this.socket = socket;
		new Thread(chatThread).start();
	}

	final Task<Void> chatThread = new Task<Void>() {
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
				System.out.println(((StringMsg) msg).getContent() + " has joined the Server!");
			}
			}
		}
	};

	public void sendChatMsg(ChatMsg msg) {
		msg.send(socket);
	}

	public String toStringName() {
		return playerName + " has joined!";
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
