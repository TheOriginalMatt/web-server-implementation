package rocks.mattjackson;

public class Response {
	private int status;
	private String body;
	
	public Response(int status, String body) {
		this.status = status;
		this.body = body;
	}
	
	public Response(int status, Render render) {
		this(status, render.getBody());
	}
	
	private String statusLine() {
		return "HTTP/1.1 " + getStatus();
	}
	
	public String toString() {
		return statusLine() + "\n\n" + getBody();
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getBody() {
		return this.body;
	}
}
