package rocks.mattjackson.response;

import java.util.Date;

public class Cookie extends Header {
	private static final String COOKIE_KEY_VALUE_DELIMITER = "=";
	private static final String COOKIE_HEADER_KEY = "Set-Cookie";
	private static final String COOKIE_SETTINGS_DELIMITER = "; ";
	private static final String COOKIE_SECURE_SETTINGS = 
			"Secure"+
			COOKIE_SETTINGS_DELIMITER
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
			headerValue += COOKIE_SETTINGS_DELIMITER + getExperation().toString();
		}
		if (isSecure()) { 
			headerValue += COOKIE_SETTINGS_DELIMITER + COOKIE_SECURE_SETTINGS;
		}
		return new Header(COOKIE_HEADER_KEY, headerValue).toString();
	}
}
