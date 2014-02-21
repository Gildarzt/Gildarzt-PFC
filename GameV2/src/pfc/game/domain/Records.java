package pfc.game.domain;

/**ARP-09/01/14: This class controls the records of the player*/
public class Records {
	private int id;
	private int value;
	
	public Records(int id, int value) {
		super();
		this.id = id;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return id+" "+value+"\n";
	}
	
}
