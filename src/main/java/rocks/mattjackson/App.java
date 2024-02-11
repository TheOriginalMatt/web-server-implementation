package rocks.mattjackson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

import rocks.mattjackson.controllers.GenericController;

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
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			
			while(true) {
				String inLine = in.readLine();
				if (inLine == null || inLine.isEmpty()) {
					break;
				}
				System.out.println("From client: "+(inLine == null ? "<< null >>" : inLine)+inLine.length());
			}
			
			String output = new GenericController().handle(null);
			System.out.println("Done reading from client");
			System.out.println("Returning the value "+output);
			out.println(output);
		} catch (IOException e) {
			System.out.println("Encountered an error: "+e.getMessage());
		}
	}
}