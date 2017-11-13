package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Socket socket = null;
	ObjectOutputStream output;
	ObjectInputStream input;
	String msg;

	public Client() {

	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);
			System.out.println("connected to localhost port 2303");

			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		Client client = new Client();
		client.run();
	}

}
