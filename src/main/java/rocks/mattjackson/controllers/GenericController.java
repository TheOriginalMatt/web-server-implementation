package rocks.mattjackson.controllers;

import rocks.mattjackson.Request;
import rocks.mattjackson.Response;

public class GenericController extends Controller {

	@Override
	public Response handle(Request request) {
		return new Response(200, "Hello World!");
	}

}
