package rocks.mattjackson.util;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import rocks.mattjackson.response.Cookie;
import rocks.mattjackson.response.Header;

public class HasHeaders {
	private List<Header> headers;
	
	public HasHeaders() {
		setHeaders(new LinkedList<>());
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	
	public Optional<Header> getHeader(String key) {
		for (Header header : headers) {
			if (header.getKey().equals(key)) {
				return Optional.of(header);
			}
		}
		return Optional.empty();
	}
	
	public void removeHeader(String key) {
		ListIterator<Header> itr = headers.listIterator();
		while(itr.hasNext()) {
			Header next = itr.next();
			if (next.getKey().equals(key)) {
				itr.remove();
				return;
			}
		}
	}
	
	public void addHeader(String key, String value) {
		getHeaders().add(new Header(key, value));
	}
	
	public void addCookie(String key, String value) {
		getHeaders().add(new Cookie(key, value));
	}
	
	protected void addHeaderFromRequest(String headerLine) {
		String[] split = headerLine.split(Header.HEADER_KEY_VALUE_DELIMITER);
		if (split.length != 2) { // I'm assuming a header key or value can't contain a colon?
			return;
		}
		String key = split[0];
		String value = split[1];
		if (!Cookie.COOKIE_REQUEST_HEADER_KEY.equals(key)) {
			addHeader(key, value);
			return;
		}
		String[] values = value.split(Header.HEADER_VALUE_DELIMITER);
		for (String headerValue : values) {
			String[] cookieData = headerValue.split(Cookie.COOKIE_KEY_VALUE_DELIMITER);
			if (cookieData.length != 2) {
				continue;
			}
			addCookie(cookieData[0], cookieData[1]);
		}
	}
}
