package rocks.mattjackson.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;

/**
 * Wrapper class for handling multiple config files. Basically we don't want a single config for everything, but instead
 * many small files split into specific concerns (i.e. configs for the server vs configs for the template language).
 * 
 * These configs are stored in {@code src/conf/whatever.properties} and store data in the format {@code key = value}.
 * 
 */
public class Configs {
	
	private static final String CONFIG_FILE_PREFIX = "./target/classes/";
	private static final String CONFIG_FILE_SUFFIX = ".properties";
	
	private static Map<String, PropertiesConfiguration> configs;
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * @return the configurations specific to the server.
	 */
	public static PropertiesConfiguration serverConfigs() {
		return getConfigs("server");
	}
	
	/**
	 * @return the configurations specific to the FreeMarker template language.
	 */
	public static PropertiesConfiguration templateConfigs() {
		return getConfigs("templates");
	}
	
	private static PropertiesConfiguration getConfigs(String fileName) {
		
		if (configs == null) {
			configs = new HashMap<>();
		}
		
		if (configs.get(fileName) == null) {
			String pathToFile = CONFIG_FILE_PREFIX+fileName+CONFIG_FILE_SUFFIX;
			try {
				configs.put(fileName, new Configurations().properties(new File(pathToFile)));
				logger.debug("Grabbed a config file at: "+pathToFile);
			} catch (ConfigurationException e) {
				logger.fatal("Unable to access the config file at "+pathToFile+". Exiting.", e);
				System.exit(1);
			}
		}
		return configs.get(fileName);
	}
}
