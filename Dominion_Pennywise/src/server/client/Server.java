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
	// ObjectInputStream input;
	// ObjectOutputStream output;
	// OutputStream output;
	// OutputStreamWriter writer;
	// BufferedWriter bw;
	// InputStream input;
	// InputStreamReader reader;
	BufferedReader br;
	static DataInputStream input;
	static DataOutputStream output;

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

				// output = new ObjectOutputStream(socket.getOutputStream());
				// input = new ObjectInputStream(socket.getInputStream());
				// output = socket.getOutputStream();
				// writer = new OutputStreamWriter(output);
				// bw = new BufferedWriter(writer);
				// input = socket.getInputStream();
				// reader = new InputStreamReader(input);

				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());

				System.out.println(input.readUTF());
				
				output.writeUTF("hiii");
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

	public static void main(String[] args) throws ClassNotFoundException {
		Server s = new Server();
		s.connect();
	}

}
