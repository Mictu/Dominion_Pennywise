package server.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import server_Models.*;

public class Client {
	Socket socket = null;
	ObjectOutputStream output;
	ObjectInputStream input;
	String msg;

	public Client() {

	}

	public void run() {
		try {
			socket = new Socket("localhost", 2303);

			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			Game_Controller gc = new Game_Controller();
			gc.firstRound();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Verbindung fehlgeschlagen");
		} finally {
			try {
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}


}
