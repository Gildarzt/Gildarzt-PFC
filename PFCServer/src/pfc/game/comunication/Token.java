package pfc.game.comunication;

/**ARP-10/03/2014: this class implement an object with two String attibutes. It's use on the Listener class to 
 * store the data from the client
 * @author Antonio
 *
 */
public class Token implements Comparable<Object>{
	private String code;
	private String value;
	private int pos;
	public Token(String code, String value, int pos) {
		super();
		this.code = code;
		this.value = value;
		this.pos = pos;
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
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	@Override
	public int compareTo(Object other) {
		int res=0;
		// TODO Auto-generated method stub
		if(other instanceof Token){
			if(this.pos>(((Token)other).getPos()))
				res=1;
			else if(this.pos<(((Token)other).getPos()))
				res=-1;
		}
		return res;
	}
	public boolean equals(Object arg0) {
		return code.equals(arg0);
	}
	
}
