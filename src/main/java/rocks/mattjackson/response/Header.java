package rocks.mattjackson.response;

public class Header {
	private static final String HEADER_KEY_VALUE_DELIMITER = ": ";
	
	private String key;
	private String value;
	
	public Header(String key, String value) {
		setKey(key);
		setValue(value);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return getKey() + HEADER_KEY_VALUE_DELIMITER + getValue();
	}
}
