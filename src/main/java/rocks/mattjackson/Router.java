package rocks.mattjackson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import rocks.mattjackson.controllers.Controller;
import rocks.mattjackson.controllers.GenericController;
import rocks.mattjackson.controllers.NotFoundController;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.response.render.RenderConfigs;
import rocks.mattjackson.util.Configs;

/**
 * Handles deciding which controller gets a request based on the path of the request, and calls it.
 */
public class Router {
	private  Map<String, Controller> routes;
	
	/**
	 * Builds the connection between the possible paths and the associated controller.
	 */
	public Router() {
		Map<String, Controller> routes = new HashMap<>();
		routes.put("/", new GenericController());
	}
	
	/**
	 * Calls the controller associated with the path in the request.
	 * 
	 * @param request The request from the user.
	 * @return The response to return to the user.
	 */
	public Response route(Request request) {
		Controller controller = routes.get(request.getPath());
		return controller != null 
				? controller.handle(request) 
				: new NotFoundController().handle(request);
	}
	

}
