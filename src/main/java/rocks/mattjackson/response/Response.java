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

public class Response extends HasHeaders{
	public Logger logger = LogManager.getLogger(ThreadedAction.class);
	private static final String NEW_LINE = "\n";
	
	private int status;
	private Render render;
	private String body;
	
	private Response(int status) {
		setHeaders(new LinkedList<>());
		this.status = status;
	}
	
	public Response(int status, String body) {
		this(status);
		this.body = body;
		
	}
	
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
	
	public String toString() {
		 return getBody();
	}
	
	public String getHeader() {
		return 
				statusLine() + NEW_LINE +
				headerLines() + NEW_LINE+
				NEW_LINE;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getBody() {
		return this.body;
	}
	
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
