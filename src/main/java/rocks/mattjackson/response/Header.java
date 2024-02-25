package rocks.mattjackson.response;

/**
 * POJO representation of an HTTP header, which is basically a key-value pair with a specific string represenation.
 */
public class Header {
	/**
	 * The delimiter that splits the key and value in the string representation of a header.
	 */
	public static final String HEADER_KEY_VALUE_DELIMITER = ": ";
	/**
	 * The delimiter between multiple values of the same header key.
	 */
	public static final String HEADER_VALUE_DELIMITER = "; ";
	
	private String key;
	private String value;
	
	/**
	 * Create a header with the given key and value.
	 * 
	 * @param key The key that this header can be found at.
	 * @param value The value stored by this header.
	 */
	public Header(String key, String value) {
		setKey(key);
		setValue(value);
	}

	/**
	 * @return The key that this header can be found at.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key The key that this header can be found at.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return The value stored by this header.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value stored by this header.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return A string represenation of this header in a form the client's browser will understand.
	 */
	public String toString() {
		return getKey() + HEADER_KEY_VALUE_DELIMITER + getValue();
	}
}
