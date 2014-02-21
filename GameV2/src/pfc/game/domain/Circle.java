package pfc.game.domain;

import com.badlogic.gdx.math.Vector3;

//ARP-09/10/13: I'm gonna create the circles in this class.
public class Circle {
	private Vector3 position;
	private Vector3 direction;
	
	/**In this constructor method I will initialize my two variable position and direction.
	position will be initialize with two variables, coordinate x and y. For direction I will
	use one variable two times, this variable is speed one. The game is in 2D, so I will use
	x-axis and y-axis*/
	public Circle(float xPos, float yPos, double xSpeed,double ySpeed){
		position=new Vector3(xPos,yPos,0);
		direction=new Vector3((float)xSpeed,(float)ySpeed,0);
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getDirection() {
		return direction;
	}

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}
	
	public boolean equals(Circle cir){
		boolean res=false;
		int x=(int)this.getPosition().x-(int)cir.getPosition().x,
			y=(int)this.getPosition().y-(int)cir.getPosition().y;
		if((x<=1 && x >=-1) && (y<=1 && y>=-1)){
			res=true;
		}
		return res;
	}
	
}
