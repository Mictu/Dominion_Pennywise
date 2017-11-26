package server.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	Socket socket = null;
//	ObjectOutputStream output;
//	ObjectInputStream input;
	OutputStream output;
	OutputStreamWriter writer;
	BufferedWriter bw;
	InputStream input;
	InputStreamReader reader;
	BufferedReader br;
	String info;
	Server server;
	
	public Client() {
		server = new Server();
	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);

//			input = new ObjectInputStream(socket.getInputStream());
//			output = new ObjectOutputStream(socket.getOutputStream());
			
//			output.flush();
			input = socket.getInputStream();
			reader = new InputStreamReader(input);
			br = new BufferedReader(reader);
			
			output = socket.getOutputStream();
			writer = new OutputStreamWriter(output);
			bw = new BufferedWriter(writer);
			
			

		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Verbindung fehlgeschlagen");
//		} finally {
//			try {
//				socket.close();
//			} catch (IOException ioException) {
//				ioException.printStackTrace();
//			}
		}
	}
	
	public void sendToServer(String msg) {
		try {
			System.out.println("nach try");
			bw.write(msg);
			System.out.println("nach utf");
			server.getMsgFromClient();
//			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	
//	public String getMsgFromServer() {
//		try {
//			info = input.readUTF();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return info;
//	}
	
}
