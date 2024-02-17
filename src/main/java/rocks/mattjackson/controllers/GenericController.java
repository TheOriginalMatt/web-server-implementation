package rocks.mattjackson.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;
import rocks.mattjackson.response.Cookie;
import rocks.mattjackson.response.Header;
import rocks.mattjackson.response.Response;
import rocks.mattjackson.response.render.Render;
import rocks.mattjackson.response.render.RenderConfigs;
import rocks.mattjackson.request.Request;

public class GenericController extends Controller {
	@Override
	public Response handle(Request request) {
		TemplateDataTest data = new TemplateDataTest();
		data.setUser("you're one cooooooool developer");
		Response response = new Response(200, new Render("index.ftlh", data, request));
		response.addCookie("user-is-cool", "maybe, check back later");
		return response;
	}

	public static class TemplateDataTest {
		private String user;

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}
	}
}
