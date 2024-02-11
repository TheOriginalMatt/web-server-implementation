package rocks.mattjackson.controllers;

public class GenericController extends Controller {

	@Override
	public String handle(String request) {
		return "Hello World!";
	}

}
