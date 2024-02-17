package rocks.mattjackson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.util.Configs;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args ) {
		int portNumber = Configs.serverConfigs().getInt("server.port");
		Logger logger = LogManager.getLogger();
		logger.debug("Server starting. Available at port "+portNumber);
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