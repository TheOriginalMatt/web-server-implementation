package rocks.mattjackson.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rocks.mattjackson.response.Cookie;
import rocks.mattjackson.response.Header;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.Render;
import rocks.mattjackson.request.Request;

public class GenericController extends Controller {
	Logger logger = LogManager.getLogger();

	@Override
	public Response handle(Request request) {
		for(Header header : request.getHeaders()) {
			String isCookie = header instanceof Cookie ? "cookie: " : "header: ";
			logger.info(isCookie+header.getKey()+", "+header.getValue());
		}
		
		Response response = new Response(200, new Render("index.html"));
		response.addCookie("user-is-cool", "maybe, check back later");
		return response;
	}

}
