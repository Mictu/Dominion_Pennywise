package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket = null;
	ObjectOutputStream output = null;
	ObjectInputStream input = null;
	String msg;

	public Client() {

	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);
			System.out.println("connected to localhost port 2303");

			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());

			do {
				try {
					msg = (String) input.readObject();
					System.out.println("server>" + msg);
					sendMessage("Hi server");
					msg = "bye";
					sendMessage(msg);
				} catch (ClassNotFoundException notFound) {
					System.err.println("data received in unknown format");
				}
			} while (!msg.equals("bye"));
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				input.close();
				output.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			output.writeObject(msg);
			output.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Client client = new Client();
		client.run();
	}

}
