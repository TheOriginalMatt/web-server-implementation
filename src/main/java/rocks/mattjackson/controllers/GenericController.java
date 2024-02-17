package rocks.mattjackson.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import rocks.mattjackson.Request;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.Render;

public class GenericController extends Controller {

	@Override
	public Response handle(Request request) {
		Response response = new Response(200, new Render("index.html"));
		response.addCookie("user-is-cool", "maybe, check back later");
		return response;
	}

}
