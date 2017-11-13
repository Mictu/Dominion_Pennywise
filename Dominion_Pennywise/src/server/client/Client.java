package server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	Socket socket = null;
	InputStreamReader reader = null;
	BufferedReader buffReader = null;
	PrintWriter print = null;
	String msg, message;

	public Client() {

	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);
			System.out.println("connected to localhost port 2303");

			reader = new InputStreamReader(socket.getInputStream());
			buffReader = new BufferedReader(reader);
			message = buffReader.readLine();
			System.out.println("Ausgabe: " + message);

			print = new PrintWriter(socket.getOutputStream());

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
