package rocks.mattjackson.response.render;

import java.io.OutputStream;

import freemarker.template.Configuration;

/**
 * A wrapper object to let us pass information from when the request is first handled, all the way back to server-side
 * rendering the response. This mostly contains the configuration for FreeMarker to handle the rendering, and the writer
 * that FreeMarker is putting the rendered HTML into to get it back to the client.
 */
public class RenderConfigs{
	private OutputStream outStream;
	private final Configuration templateConfig;
	
	/**
	 * @param templateConfig The Configuration needed by FreeMarker to eventually render the response to this request.
	 */
	public RenderConfigs(Configuration templateConfig) {
		this.templateConfig = templateConfig;
	}

	/**
	 * @return The output stream that the response will be streamed to the user via.
	 */
	public OutputStream getOutStream() {
		return outStream;
	}

	/**
	 * @param outStream The output stream that the response will be streamed to the user via.
	 */
	public void setOutStream(OutputStream outStream) {
		this.outStream = outStream;
	}

	/**
	 * @return he Configuration needed by FreeMarker to eventually render the response to this request.
	 */
	public Configuration getTemplateConfig() {
		return templateConfig;
	}
}
