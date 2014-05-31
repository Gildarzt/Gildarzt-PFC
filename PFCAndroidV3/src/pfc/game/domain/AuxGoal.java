package pfc.game.domain;
/**ARP-31/01/2014 This class is used to show the goals on the profile page. 
 * It allows to show if the goal has been gotten or not.
 * This class will be use to the records too, but with the difference that the value of state always will be false.
 * @author Antonio
 *
 */
public class AuxGoal {
	private String name;
	private String descp;
	private boolean state;
	
	public AuxGoal(String name, String descp, boolean state) {
		super();
		this.name=name;
		this.descp = descp;
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
