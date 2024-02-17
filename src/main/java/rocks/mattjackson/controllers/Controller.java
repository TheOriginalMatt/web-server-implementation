package rocks.mattjackson.controllers;

import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;

public abstract class Controller {
	public abstract Response handle(Request request);
}