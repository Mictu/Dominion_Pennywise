package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import server_Models.Game_Controller;

public class Server {

	Socket socket = null;
	ServerSocket server = null;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	Game_Controller game;

	public Server() {

	}

	public void connect() throws ClassNotFoundException {

		try {
			server = new ServerSocket(2303);
			System.out.println("Waiting for Connection");

			InetAddress iAddress = InetAddress.getLocalHost();
			System.out.println("Server IP address : " + iAddress.getHostAddress());

			while (true) {
				socket = server.accept();
				System.out.println("Connection received from: " + socket.getInetAddress().getHostName());

				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				
				String text = input.readUTF();
				System.out.println(text);
				
				game = new Game_Controller();
			}
		}

		catch (IOException e) {
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

	public void writeObject() {

	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Server().connect();
	}

}
