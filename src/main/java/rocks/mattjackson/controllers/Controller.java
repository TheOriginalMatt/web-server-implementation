package rocks.mattjackson.controllers;

import freemarker.template.Configuration;
import rocks.mattjackson.request.Request;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.response.render.RenderConfigs;

public abstract class Controller {
	public abstract Response handle(Request request);
}