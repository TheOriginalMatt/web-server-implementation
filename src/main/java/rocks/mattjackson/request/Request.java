package rocks.mattjackson.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.response.Cookie;
import rocks.mattjackson.response.Header;
import rocks.mattjackson.response.render.RenderConfigs;
import rocks.mattjackson.util.HasHeaders;

public class Request extends HasHeaders {
	private final String method;
	private final String path;
	private final RenderConfigs renderConfigs;
	
	public Request(BufferedReader requestReader, RenderConfigs renderConfigs) throws IOException {
		super();
		this.renderConfigs = renderConfigs;
		String firstLine = requestReader.readLine();
		String[] split = firstLine.split(" ");
		method = split[0];
		path = split[1];
		while(true) {
			String inLine = requestReader.readLine();
			if (inLine == null || inLine.isEmpty()) {
				break;
			}
			addHeaderFromRequest(inLine);
		}
	}
	
	public String getHttpMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}
	
	public RenderConfigs getRenderConfigs() {
		return this.renderConfigs;
	}
}
