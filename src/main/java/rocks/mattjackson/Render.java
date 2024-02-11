package rocks.mattjackson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Render {
	
	private String body;
	
	public Render(String file) {
		try {
			body = Files.readString(Paths.get("target/classes/public/"+file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBody() {
		return body;
	}
}
