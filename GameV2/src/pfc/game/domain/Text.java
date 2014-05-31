package pfc.game.domain;

import com.badlogic.gdx.math.Vector3;

/**ARP-13/11/12: This class implements the words than appear in the sreen*/
public class Text {
	private Vector3 position;
	
	public Text(float posX,float posY){
		position=new Vector3(posX,posY,0);
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}
	

}
