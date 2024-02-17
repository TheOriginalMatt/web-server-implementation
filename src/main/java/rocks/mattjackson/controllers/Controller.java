package rocks.mattjackson.controllers;

import rocks.mattjackson.Request;
import rocks.mattjackson.response.Response;

public abstract class Controller {
	public abstract Response handle(Request request);
}