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

public class Configs {
	
	private static final String CONFIG_FILE_PREFIX = "./target/classes/";
	private static final String CONFIG_FILE_SUFFIX = ".properties";
	
	private static Map<String, PropertiesConfiguration> configs;
	private static Logger logger = LogManager.getLogger();
	
	public static PropertiesConfiguration serverConfigs() {
		return getConfigs("server");
	}
	
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
