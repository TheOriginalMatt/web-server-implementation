package rocks.mattjackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.Router;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;

public class ThreadedAction {
	public Logger logger = LogManager.getLogger(ThreadedAction.class);

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
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			Request request = new Request(in);
			logRequest(request);
			Response response = router.route(request);
			out.println(response.toString());
		} catch (IOException e) {
			logger.error("Unable to run ThreadedAction", e);
		}
	}

	private void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			logger.error("Unable to close the socket", e);
		}
	}

	private void logRequest(Request request) {
		logger.debug("RQST: "+request.getHttpMethod()+" | "+request.getPath());
	}
}
