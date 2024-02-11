package rocks.mattjackson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		int portNumber = 8080;
		System.out.println("Server starting. Available at localhost:"+portNumber);
		ThreadedAction actor = new ThreadedAction(4);
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
		) {
			while(true) {
				Socket socket = serverSocket.accept();
				actor.act(socket);
			}
		} catch (IOException e) {
			System.out.println("Encountered an error: "+e.getMessage());
		}
	}
}