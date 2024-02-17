package rocks.mattjackson;

import java.util.HashMap;
import java.util.Map;

import rocks.mattjackson.controllers.Controller;
import rocks.mattjackson.controllers.GenericController;
import rocks.mattjackson.controllers.NotFoundController;
import rocks.mattjackson.response.Response;

public class Router {
	private  Map<String, Controller> routes;
	
	public static Router build() {
		Map<String, Controller> routes = new HashMap<>();
		routes.put("/", new GenericController());
		
		
		
		return new Router(routes);
	}
	
	private Router(Map<String, Controller> routes) {
		this.routes = routes;
	}
	
	public Response route(Request request) {
		Controller controller = routes.get(request.getPath());
		return controller != null ? controller.handle(request) : new NotFoundController().handle(request);
	}
}
