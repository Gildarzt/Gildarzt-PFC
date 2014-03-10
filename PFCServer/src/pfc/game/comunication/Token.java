package pfc.game.comunication;
/**ARP-10/03/2014: this class implement an object with two String attibutes. It's use on the Listener class to 
 * store the data from the client
 * @author Antonio
 *
 */
public class Token {
	private String code;
	private String value;
	public Token(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
