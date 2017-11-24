package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server_Models.Game_Controller;
import server_Models.Player;

public class Server {

	Socket socket = null;
	ServerSocket server = null;
	ObjectInputStream input;
	ObjectOutputStream output;

	Game_Controller game;
	Player player;
	String msg;

	public Server() {
		player = new Player();

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
	
	public String getMsgFromClient() {
		try {
			msg = input.readObject().toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(msg);
		return msg;
	}
	
	public void sendToClient(String info) {
		try {
			output.writeObject(info);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws ClassNotFoundException {
		new Server().connect();
	}

}
