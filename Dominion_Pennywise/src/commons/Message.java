package commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Message abstract class for any type of messages to send to client and server
 * and to receive on client and server Few Changes made by Michael Tu
 * 
 * @Source ChatLab SoftwareEngineering 2
 * 
 * @author Brad Richards
 * @author Michael Tu
 *
 */
public abstract class Message {

	protected MessageType type;

	/**
	 * Message Constructor, define type
	 * @param type
	 * @author Brad Richards
	 */
	public Message(MessageType type) {
		this.type = type;
	}

	/**
	 * send() in Message abstract class. Takes parameter the socket as parameter to
	 * send to the right socket
	 * 
	 * @param socket
	 */
	public void send(Socket socket) {
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(socket.getOutputStream());
			out.write(this.toString() + "\n");
			System.out.println("Sending: " + this);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Message receive() in Message abstract class. This method receive messages
//	 * either on client or server and checks what type the message is. Then it sets the Message: Type|Name|message
	 * 
	 * @param socket
	 * @return msg
	 * 
	 * @author Brad Richards
	 * @author Michael Tu
	 */
	public static Message receive(Socket socket) {
		BufferedReader in;
		Message msg = null;
		if (socket != null) {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msgText = in.readLine();
				System.out.println("Receiving: " + msgText);
				// Parse message
				String[] parts = msgText.split("\\|");
				if (parts[0].equals(MessageType.Join.toString())) {
					msg = new JoinMsg(parts[1]);
				} else if (parts[0].equals(MessageType.Chat.toString())) {
					msg = new ChatMsg(parts[1], parts[2]);
				} else if (parts[0].equals(MessageType.String.toString())) {
					msg = new StringMsg(parts[1], parts[2]);
				} else if (parts[0].equals(MessageType.Close.toString())) {
					msg = new CloseMsg(parts[1], parts[2]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	public MessageType getType() {
		return type;
	}
}
