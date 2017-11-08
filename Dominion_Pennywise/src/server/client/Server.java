package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	Socket socket = null;
	ServerSocket server = null;
	ObjectInputStream input;
	ObjectOutputStream output;
	String msg;

	public Server() {

	}

	public void connect() {
		try {
			server = new ServerSocket(2303);
			System.out.println("Waiting for Connection");

			socket = server.accept();

			System.out.println("Connection received from: " + socket.getInetAddress().getHostName());

			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());

			sendMsg("Connection successful");

			do {
				try {
					msg = (String) input.readObject();
					System.out.println("client:>" + msg);

					if (msg.equals("bye")) {
						sendMsg("bye");
					}
				} catch (ClassNotFoundException notFound) {
					System.err.println("Class not found");
				}
			} while (!msg.equals("bye"));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				output.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String msg) {
		try {
			output.writeObject(msg);
			output.flush();
			System.out.println("server:>" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.connect();
	}

}
