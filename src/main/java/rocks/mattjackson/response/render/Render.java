package rocks.mattjackson.response.render;

import java.io.File;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TimeZone;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.util.Configs;

/**
 * Handles server-side rendering an {@code .ftlh} file.
 */
public class Render {
	Logger logger = LogManager.getLogger();
	
	private Template template;
	Object data;
	
	/**
	 * @param file The {@code .ftlh} file to be rendered.
	 * @param request The request that the rendered file is associated with.
	 */
	public Render(String file, Request request) {
		this(file, null, request);
	}
	
	/**
	 * @param file The {@code .ftlh} file to be rendered.
	 * @param data the data, either in a {@code Map<String, String>} format, or as a POJO, to be passed to the
	 * 			   template.
	 * @param request The request that the rendered file is associated with.
	 */
	public Render(String file, Object data, Request request) {
		try {
			this.data = data;
			template = request.getRenderConfigs().getTemplateConfig().getTemplate(file);
		} catch (IOException e) {
			logger.error("Unable to read the template file at: "+file, e);
		}
	}
	
	/**
	 * Renders the given template with the data.
	 * 
	 * @param writer the {@code Writer} that passes the strings back to the client.
	 */
	public void render(Writer writer) {
		try {
			template.process(data, writer);
		} catch (TemplateException | IOException e) {
			logger.error("Unable to render the template file.", e);
		}
	}
}
