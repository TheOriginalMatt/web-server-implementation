package rocks.mattjackson.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import rocks.mattjackson.Request;
import rocks.mattjackson.Response;
import rocks.mattjackson.Render;

public class GenericController extends Controller {

	@Override
	public Response handle(Request request) {
		return new Response(200, new Render("index.html"));
	}

}
