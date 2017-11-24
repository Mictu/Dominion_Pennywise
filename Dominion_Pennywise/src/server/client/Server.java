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
	String name;

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

	public void setPlayersName() {
		try {
			String text = input.readUTF();
			player.setName(text);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showPlayersCardsOnHand() {
		try {
			output.writeObject(player.hand);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPlayersName() {
		try {
			output.writeObject(player.getName());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Server().connect();
	}

}
