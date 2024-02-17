package rocks.mattjackson.response.render;

import java.io.OutputStream;

import freemarker.template.Configuration;

public class RenderConfigs{
	private OutputStream outStream;
	private final Configuration templateConfig;
	
	public RenderConfigs(Configuration templateConfig) {
		this.templateConfig = templateConfig;
	}

	public OutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(OutputStream outStream) {
		this.outStream = outStream;
	}

	public Configuration getTemplateConfig() {
		return templateConfig;
	}
}
