package rocks.mattjackson.util;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import rocks.mattjackson.response.Cookie;
import rocks.mattjackson.response.Header;

/**
 * Base class to handle storing, setting, and retrieving headers and cookies for requests and responses.
 */
public class HasHeaders {
	private List<Header> headers;
	
	/**
	 * Initializes the header and cookie list to be empty.
	 */
	public HasHeaders() {
		setHeaders(new LinkedList<>());
	}

	/**
	 * @return The list of headers and cookies.
	 */
	public List<Header> getHeaders() {
		return headers;
	}

	/**
	 * @param headers Sets the cookies and headers to the given value.
	 */
	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	
	/**
	 * Gets the header associated with the given key, if it exists.
	 * 
	 * @param key The key for the desired header or cookie.
	 * @return The header wrapped in an {@code Optional} if it exists, otherwise {@code Optional.empty()}.
	 */
	public Optional<Header> getHeader(String key) {
		for (Header header : headers) {
			if (header.getKey().equals(key)) {
				return Optional.of(header);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Removes the header or cookie with the given key from the request.
	 * 
	 * @param key The key associated with the header or cookie to remove.
	 */
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
	
	/**
	 * Adds a header with the given key-value pair.
	 * 
	 * @param key The key to get the header at.
	 * @param value The value stored by the header.
	 */
	public void addHeader(String key, String value) {
		getHeaders().add(new Header(key, value));
	}
	
	/**
	 * Adds a cookie with the given key-value pair. The cookie will not expire and will not be marked secure. If you
	 * want either of those to be different, manually create a {@link Cookie} and add it to the list.
	 * 
	 * @param key The key the cookie is stored at.
	 * @param value The value stored by the cookie.
	 */
	public void addCookie(String key, String value) {
		getHeaders().add(new Cookie(key, value));
	}
	
	/**
	 * Handles de-serializing the string representation of the header and cookie into the object form.
	 * 
	 * @param headerLine The individual line of the request to be converted into headers and cookies.
	 */
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
