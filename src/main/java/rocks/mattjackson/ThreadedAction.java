package rocks.mattjackson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.response.render.RenderConfigs;
import rocks.mattjackson.util.Configs;

public class ThreadedAction {
	public Logger logger = LogManager.getLogger();

	private Executor executor;
	private Router router;
	private Configuration freeMarkerConfigs;

	public ThreadedAction(int threadPoolSize) {
		freeMarkerConfigs = generateFreeMarkerConfigs();
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
			RenderConfigs configs = new RenderConfigs(freeMarkerConfigs);
			Request request = new Request(in, configs);
			logRequest(request);
			Response response = router.route(request);
			response.sendToClient(out);
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
	
	public static Configuration generateFreeMarkerConfigs() {
		// This is stolen from https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
		
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.32) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		Configuration configs = new Configuration(Configuration.VERSION_2_3_32);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		try {
			configs.setDirectoryForTemplateLoading(new File(Configs.templateConfigs().getString("location")));
		} catch (IOException e) {
			
		}

		// From here we will set the settings recommended for new projects. These
		// aren't the defaults for backward compatibilty.

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		configs.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		configs.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		configs.setLogTemplateExceptions(false);

		// Wrap unchecked exceptions thrown during template processing into TemplateException-s:
		configs.setWrapUncheckedExceptions(true);

		// Do not fall back to higher scopes when reading a null loop variable:
		configs.setFallbackOnNullLoopVariable(false);

		// To accomodate to how JDBC returns values; see Javadoc!
		configs.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
		
		return configs;
	}
}
