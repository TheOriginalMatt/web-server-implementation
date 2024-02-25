package rocks.mattjackson.request;

import java.io.BufferedReader;
import java.io.IOException;
import rocks.mattjackson.response.render.RenderConfigs;
import rocks.mattjackson.util.HasHeaders;
import rocks.mattjackson.response.render.Render;

/**
 * POJO representation of the request sent to the server. Also holds information that needs to be used further along in
 * handling the request, namely the configs to get the server-side rendering to work.
 */
public class Request extends HasHeaders {
	private final String method;
	private final String path;
	private final RenderConfigs renderConfigs;
	
	/**
	 * Create the request from the reader and the appropriate configs
	 * 
	 * @param requestReader The reader for the given request.
	 * @param renderConfigs The render configs for this request. I can't image they'll be different per request, but 
	 * 		  this protects us against global state.
	 * @throws IOException if the request cannot be properly read.
	 */
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
	
	/**
	 * @return This method's HTTP method.
	 */
	public String getHttpMethod() {
		return method;
	}

	/**
	 * @return The path associated with this request. Currently includes query params and fragments.
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @return The render configurations for when the server-side rendering happens. Probably shouldn't be used in the
	 * normal handling of a request, outside of {@link Render}.
	 */
	public RenderConfigs getRenderConfigs() {
		return this.renderConfigs;
	}
}
