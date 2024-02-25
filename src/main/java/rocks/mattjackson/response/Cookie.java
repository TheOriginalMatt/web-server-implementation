package rocks.mattjackson.response;

import java.util.Date;

/**
 * POJO representation of a cookie. Note the abstraction that a cookie is a header, which isn't <em>exactly</em> true,
 * but since a cookie is always handled within a header this allows a simpler mental model when considering them on the
 * back-end.
 */
public class Cookie extends Header {
	/**
	 * Stores the delimiter used between the key and the value in the string representation of the cookie.
	 */
	public static final String COOKIE_KEY_VALUE_DELIMITER = "=";
	/**
	 * The header key value when sending cookies in responses.
	 */
	public static final String COOKIE_RESPONSE_HEADER_KEY = "Set-Cookie";
	/**
	 * The header key value when getting a cookie in the request.
	 */
	public static final String COOKIE_REQUEST_HEADER_KEY = "Cookie";
	/**
	 * The string value that denotes to browsers a cookie is secure and shouldn't be available to the JS.
	 */
	public static final String COOKIE_SECURE_SETTINGS = 
			"Secure"+
			Header.HEADER_VALUE_DELIMITER
			+"HttpOnly";
	
	private Date expiration;
	private boolean secure;
	
	/**
	 * Creates a cookie containing the key-value pair. This cookie will not expire and is not marked as secure so it is
	 * available to the JS running in the browser.
	 * 
	 * @param key The key the value can be retrieved from.
	 * @param value The value stored in the cookie.
	 */
	public Cookie(String key, String value) {
		this(key, value, null, false);
	}
	
	/**
	 * Creates a cookie containing the key-value pair, with the given values.
	 * 
	 * @param key The key the value can be retrieved from.
	 * @param value The value stored in the cookie.
	 * @param expiration When the cookie will expire.
	 * @param secure {@code true} if the cookie should be marked secure and not available to the JS on the page, 
	 * 		  otherwise {@code false}.
	 */
	public Cookie(String key, String value, Date expiration, boolean secure) {
		super(key, value);
		setExpiration(expiration);
		setSecure(secure);
	}

	/**
	 * @return When the browser should expire the cookie.
	 */
	public Date getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration When the browser should expire the cookie.
	 */
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return {@code true} if the cookie should be marked secure and not available to the JS on the page, 
	 * 		   otherwise {@code false}.
	 */
	public boolean isSecure() {
		return secure;
	}

	/**
	 * @param secure {@code true} if the cookie should be marked secure and not available to the JS on the page, 
	 * 				 otherwise {@code false}.
	 */
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	@Override
	/**
	 * @return The string representation of this cookie in the form readable by the client's browser.
	 */
	public String toString() {
		String headerValue = getKey()+COOKIE_KEY_VALUE_DELIMITER+getValue();
		if (getExpiration() != null) {
			headerValue += Header.HEADER_VALUE_DELIMITER + getExpiration().toString();
		}
		if (isSecure()) { 
			headerValue += Header.HEADER_VALUE_DELIMITER  + COOKIE_SECURE_SETTINGS;
		}
		return new Header(COOKIE_RESPONSE_HEADER_KEY, headerValue).toString();
	}
}
