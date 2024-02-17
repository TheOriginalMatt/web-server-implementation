package rocks.mattjackson.response;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import rocks.mattjackson.Render;

import java.util.Optional;

public class Response {
	private static final String NEW_LINE = "\n";
	
	private int status;
	private String body;
	private List<Header> headers;
	
	public Response(int status, String body) {
		this.status = status;
		this.body = body;
		setHeaders(new LinkedList<>());
	}
	
	public Response(int status, Render render) {
		this(status, render.getBody());
	}
	
	private String statusLine() {
		return "HTTP/1.1 " + getStatus();
	}
	
	private String headerLines() {
		StringBuilder builder = new StringBuilder();
		for (Header header : headers) {
			builder.append(header.toString()).append(NEW_LINE);
		}
		return builder.toString();
	}
	
	public String toString() {
		return 
				statusLine() + NEW_LINE +
				headerLines() + NEW_LINE+
				NEW_LINE + 
				getBody();
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getBody() {
		return this.body;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	
	public void addHeader(String key, String value) {
		getHeaders().add(new Header(key, value));
	}
	
	public void addCookie(String key, String value) {
		getHeaders().add(new Cookie(key, value));
	}
}
