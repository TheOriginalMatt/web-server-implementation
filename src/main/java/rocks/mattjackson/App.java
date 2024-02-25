package rocks.mattjackson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.util.Configs;

/**
 * The application. Listens to for requests and gives them to a threaded actor to be handled.
 */
public class App {
	/**
	 * Runs the web-server. Just passes on each request it gets.
	 * 
	 * @param args These arguments aren't used. Instead use {@link Configs}
	 */
	public static void main( String[] args ) {
		int portNumber = Configs.serverConfigs().getInt("port");
		Logger logger = LogManager.getLogger();
		logger.debug("Server starting. Available at port "+portNumber);
		ThreadedAction actor = new ThreadedAction(Configs.serverConfigs().getInt("requestHandlingThreads"));
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