package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

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

			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();

			msg = JOptionPane.showInputDialog("Enter something: ");
			output.writeObject(msg);

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
