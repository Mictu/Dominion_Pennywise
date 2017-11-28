package server.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server_Models.Player;

public class Server {

	static Socket socket = null;
	static ServerSocket server = null;
	BufferedReader br;
	static DataInputStream input;
	static DataOutputStream output;
	
	ServerHandler sh = new ServerHandler();

	Player player;
	String msg;

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

				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
				
				msg = input.readUTF();

				sh.getMessageFromServer(msg);
				
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

	// public void getStringFromClient() {
	// try {
	// String testString = input.readUTF();
	// System.out.println(testString);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public static void main(String[] args) throws ClassNotFoundException {
		Server s = new Server();
		s.connect();
	}

}
