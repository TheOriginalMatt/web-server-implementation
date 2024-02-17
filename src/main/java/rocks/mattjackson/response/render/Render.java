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

public class Render {
	Logger logger = LogManager.getLogger();
	
	private Template template;
	Object data;
	
	public Render(String file, Request request) {
		this(file, null, request);
	}
	
	public Render(String file, Object data, Request request) {
		try {
			this.data = data;
			template = request.getRenderConfigs().getTemplateConfig().getTemplate(file);
		} catch (IOException e) {
			logger.error("Unable to read the template file at: "+file, e);
		}
	}
	
	public void render(Writer writer) {
		try {
			template.process(data, writer);
		} catch (TemplateException | IOException e) {
			logger.error("Unable to render the template file.", e);
		}
	}
}
