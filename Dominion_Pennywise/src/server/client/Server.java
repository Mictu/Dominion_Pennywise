package server.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server_Models.Player;

public class Server {

	Socket socket = null;
	ServerSocket server = null;
//	ObjectInputStream input;
//	ObjectOutputStream output;
	OutputStream output;
	OutputStreamWriter writer;
	BufferedWriter bw;
	InputStream input;
	InputStreamReader reader;
	BufferedReader br;

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

//				output = new ObjectOutputStream(socket.getOutputStream());
//				input = new ObjectInputStream(socket.getInputStream());
				output = socket.getOutputStream();
				writer = new OutputStreamWriter(output);
				bw = new BufferedWriter(writer);
				input = socket.getInputStream();
				reader = new InputStreamReader(input);
				br = new BufferedReader(reader);
				
				

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
		System.out.println("Endlich server");
		try {
			msg = br.readLine();
		} catch (IOException e) {
			System.out.println("shit");
			e.printStackTrace();
		}
		System.out.println("HIER LÄUFTS");
		System.out.println(msg);
		return msg;
	}
	
//	public void sendToClient(String info) {
//		try {
//			output.writeUTF(info);
//			output.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}


	public static void main(String[] args) throws ClassNotFoundException {
		new Server().connect();
	}

}
