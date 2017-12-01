package server.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import controllers.ClientHandler;

public class Client {
	Socket socket = null;
	 ObjectOutputStream objectOutput;
	 ObjectInputStream objectInput;
	// OutputStream output;
	// OutputStreamWriter writer;
	// BufferedWriter bw;
	// InputStream input;
	// InputStreamReader reader;
	// BufferedReader br;
	DataInputStream input;
	DataOutputStream output;
	String info;
	Server server;
	ClientHandler clientH;
	ArrayList<String> deck = new ArrayList<String>();

	public Client() {
		server = new Server();
	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);

			// input = new ObjectInputStream(socket.getInputStream());
			// output = new ObjectOutputStream(socket.getOutputStream());

			// output.flush();
			// input = socket.getInputStream();
			// reader = new InputStreamReader(input);
			// br = new BufferedReader(reader);
			//
			// output = socket.getOutputStream();
			// writer = new OutputStreamWriter(output);
			// bw = new BufferedWriter(writer);

			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());

		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Verbindung fehlgeschlagen");
			// } finally {
			// try {
			// socket.close();
			// } catch (IOException ioException) {
			// ioException.printStackTrace();
			// }
		}
	}

	public void sendToServer(String msg) {
		try {
			output.writeUTF(msg);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getStringFromServer() {
		try {
			System.out.println(input.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getArrayFromServer() {
		try {
			objectInput = new ObjectInputStream(socket.getInputStream());
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			
			clientH = new ClientHandler();
			
			this.deck = (ArrayList<String>) objectInput.readObject();
			
			clientH.getDeckFromServer(deck);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	// public String getMsgFromServer() {
	// try {
	// info = input.readUTF();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return info;
	// }

}
