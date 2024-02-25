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

/**
 * A test example of how a controller would actually handle a response and return sever-rendered HTML.
 */
public class GenericController implements Controller {
	@Override
	public Response handle(Request request) {
		TemplateDataTest data = new TemplateDataTest();
		data.setUser("dev! You even know docker! You're so hip!");
		Response response = new Response(200, new Render("index.ftlh", data, request));
		response.addCookie("user-is-cool", "maybe, check back later");
		return response;
	}

	/**
	 * We can just pass any object into Render and the template can use it to be rendered!
	 */
	public static class TemplateDataTest {
		private String user;

		/**
		 * @return the user
		 */
		public String getUser() {
			return user;
		}

		/**
		 * @param user the user
		 */
		public void setUser(String user) {
			this.user = user;
		}
	}
}
