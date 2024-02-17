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
		Logger logger = LogManager.getLogger(ThreadedAction.class);
		int portNumber = 8080;
		logger.fatal("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
		logger.fatal("I'm logging at the fatal level");
		logger.error("I'm logging at the error level");
		logger.warn("I'm logging at the warn level");
		logger.info("I'm logging at the info level");
		logger.debug("I'm logging at the debug level");
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