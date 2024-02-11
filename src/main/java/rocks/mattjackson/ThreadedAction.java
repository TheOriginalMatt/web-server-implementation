package rocks.mattjackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import rocks.mattjackson.Router;

public class ThreadedAction {
	
	private Executor executor;
	private Router router;
	
	public ThreadedAction(int threadPoolSize) {
		executor = Executors.newFixedThreadPool(threadPoolSize);
		router = Router.build();
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
				
				Response response = router.route(request);
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
