package rocks.mattjackson.response;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.ThreadedAction;
import rocks.mattjackson.response.render.Render;
import rocks.mattjackson.util.HasHeaders;

import java.util.Optional;

/**
 * POJO representation of the string returned to the user. Also handles creating the string response to user's from the 
 * values provided.
 */
public class Response extends HasHeaders{
	private Logger logger = LogManager.getLogger(ThreadedAction.class);
	private static final String NEW_LINE = "\n";
	
	private int status;
	private Render render;
	private String body;
	
	/**
	 * Create a response with the given status code and an empty body.
	 * 
	 * @param status The status code to return to the browser.
	 */
	private Response(int status) {
		setHeaders(new LinkedList<>());
		this.status = status;
	}
	
	/**
	 * Creates a response with the given status code and a body containing just the string given.
	 * 
	 * @param status The status code to return to the browser.
	 * @param body The body of the request.
	 */
	public Response(int status, String body) {
		this(status);
		this.body = body;
		
	}
	
	/**
	 * Creates a response with the given status code and the body containing the rendered content of the {@link Render}.
	 * 
	 * @param status The status code to return to the browser.
	 * @param render The server-side rendered body.
	 */
	public Response(int status, Render render) {
		this(status);
		this.render = render;
	}
	
	private String statusLine() {
		return "HTTP/1.1 " + getStatus();
	}
	
	private String headerLines() {
		StringBuilder builder = new StringBuilder();
		for (Header header : getHeaders()) {
			builder.append(header.toString()).append(NEW_LINE);
		}
		return builder.toString();
	}
	
	/**
	 * @return The headers for this request formatted in a way understood by the client's browser.
	 */
	public String getHeader() {
		return 
				statusLine() + NEW_LINE +
				headerLines() + NEW_LINE+
				NEW_LINE;
	}
	
	/**
	 * @return The status code to be returned to the user.
	 */
	public int getStatus() {
		return this.status;
	}
	
	/**
	 * @return The string body. If the body was not given as a string, i.e. if the {@code Response} was created via 
	 * {@link #Response(int, Render)}, this will return {@code null}.
	 */
	public String getBody() {
		return this.body;
	}
	
	/**
	 * Sends this response to the client by streaming it line for line to the given writer. This shouldn't be used in 
	 * the day-to-day handling of requests.
	 * 
	 * @param writer The writer used to stream the respose back to the user.
	 */
	public void sendToClient(Writer writer) {
		try {
			writer.append(getHeader());
			if (body != null) {
				writer.append(body);
			} else {
				render.render(writer);
			}
		} catch (IOException e) {
			logger.error("Unable to return a response to the client", e);
		}
		
	}
}
