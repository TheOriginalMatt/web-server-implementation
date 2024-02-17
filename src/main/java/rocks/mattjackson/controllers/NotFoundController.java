package rocks.mattjackson.controllers;

import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;

public class NotFoundController extends Controller {
	@Override
	public Response handle(Request request) {
		return new Response(404, "The page could not be found.");
	}
}
