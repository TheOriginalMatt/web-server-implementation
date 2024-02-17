package rocks.mattjackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Request {
	private final String method;
	private final String path;
	private final Map<String, String> headers;
	
	public Request(BufferedReader requestReader) throws IOException {
		String firstLine = requestReader.readLine();
		String[] split = firstLine.split(" ");
		method = split[0];
		path = split[1];
		headers = new HashMap<>();
		while(true) {
			String inLine = requestReader.readLine();
			if (inLine == null || inLine.isEmpty()) {
				break;
			}
			String[] header = inLine.split(": ");
			headers.put(header[0], header[1]);
		}
	}
	
	public String getHttpMethod() {
		return method;
	}
	
	public String getPath() {
		return path;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
}
