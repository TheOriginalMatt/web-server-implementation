package rocks.mattjackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import rocks.mattjackson.controllers.GenericController;

public class ThreadedAction {
	
	private Executor executor;
	
	public ThreadedAction(int threadPoolSize) {
		executor = Executors.newFixedThreadPool(threadPoolSize);
	}
	
	public void act(final Socket socket) {
		executor.execute(() -> {
			sendToController(socket);
			closeSocket(socket);
		});
	}
	
	private void sendToController(Socket socket) {
		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			) {
				Request request = new Request(in);
				
				System.out.println(request.getHttpMethod()+" - "+request.getPath());
				
				Response response = new GenericController().handle(null);
				System.out.println("Done reading from client");
				System.out.println("Returning the value "+response);
				out.println(response.toString());
			} catch (IOException e) {
				System.out.println("Unable to run ThreadedAction "+e);
			}
	}
	
	private void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Unable to close socket "+e);
		}
	}
}
