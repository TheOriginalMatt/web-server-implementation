package rocks.mattjackson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) {
		int portNumber = 8080;
		Logger logger = LogManager.getLogger();
		logger.debug("Server starting. Available at localhost:"+portNumber);
		ThreadedAction actor = new ThreadedAction(4);
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
		) {
			while(true) {
				Socket socket = serverSocket.accept();
				actor.act(socket);
			}
		} catch (IOException e) {
			logger.error("Encountered an error", e);
		}
	}
}