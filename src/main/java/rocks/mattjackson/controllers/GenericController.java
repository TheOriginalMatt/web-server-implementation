package rocks.mattjackson.controllers;

import rocks.mattjackson.Response;

public class GenericController extends Controller {

	@Override
	public Response handle(String request) {
		return new Response(404, "Hello World!");
	}

}
