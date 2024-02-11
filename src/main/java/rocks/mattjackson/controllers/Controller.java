package rocks.mattjackson.controllers;

import rocks.mattjackson.Request;
import rocks.mattjackson.Response;

public abstract class Controller {
	public abstract Response handle(Request request);
}