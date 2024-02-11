package rocks.mattjackson.controllers;

import rocks.mattjackson.Response;

public abstract class Controller {
	public abstract Response handle(String request);
}