package commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public abstract class Message {
	
	protected MessageType type;

	public Message(MessageType type) {
		this.type = type;
	}
	
	public void send(Socket socket) {
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(socket.getOutputStream());
			out.write(this.toString() + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Message receive(Socket socket) {
		BufferedReader in;
		Message msg = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msgText = in.readLine();
//			System.out.println(msgText);
			//Parse message
			String[] parts = msgText.split("\\|");
			if (parts[0].equals(MessageType.Join.toString())) {
				msg = new JoinMsg(parts[1]);
			} else if (parts[0].equals(MessageType.Chat.toString())) {
				msg = new ChatMsg(parts[1], parts[2]);
			} else if (parts[0].equals(MessageType.String.toString())) {
				msg = new StringMsg(parts[1],parts[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public MessageType getType() {
		return type;
	}
}
