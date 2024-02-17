package rocks.mattjackson.response;

import java.util.Date;

public class Cookie extends Header {
	public static final String COOKIE_KEY_VALUE_DELIMITER = "=";
	public static final String COOKIE_RESPONSE_HEADER_KEY = "Set-Cookie";
	public static final String COOKIE_REQUEST_HEADER_KEY = "Cookie";
	public static final String COOKIE_SECURE_SETTINGS = 
			"Secure"+
			Header.HEADER_VALUE_DELIMITER
			+"HttpOnly";
	
	private Date experation;
	private boolean secure;
	
	public Cookie(String key, String value) {
		this(key, value, null, false);
	}
	
	public Cookie(String key, String value, Date experation, boolean secure) {
		super(key, value);
		setExperation(experation);
		setSecure(secure);
	}

	public Date getExperation() {
		return experation;
	}

	public void setExperation(Date experation) {
		this.experation = experation;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	@Override
	public String toString() {
		String headerValue = getKey()+COOKIE_KEY_VALUE_DELIMITER+getValue();
		if (getExperation() != null) {
			headerValue += Header.HEADER_VALUE_DELIMITER + getExperation().toString();
		}
		if (isSecure()) { 
			headerValue += Header.HEADER_VALUE_DELIMITER  + COOKIE_SECURE_SETTINGS;
		}
		return new Header(COOKIE_RESPONSE_HEADER_KEY, headerValue).toString();
	}
}
