package rocks.mattjackson.controllers;

import freemarker.template.Configuration;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.response.render.RenderConfigs;

/**
 * The abstract controller interface. Anything that wants to handle a request should extend this.
 * 
 * Since it's a {@code FunctionalInterface}, you could do cool things like
 * <pre>
 * (request) -> new Response(418, "Make your own coffee")
 * </pre>
 */
@FunctionalInterface
public interface Controller {
	/**
	 * How each controller handles a response.
	 * 
	 * @param request The request being handled.
	 * @return The {@link Response} to return to the client.
	 */
	Response handle(Request request);
}