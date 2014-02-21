package pfc.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	private double difficult;
	private int tries;
	private List<Circle> ArrayCircle;
	
	public Test(double difficult){
		if(difficult >=3){
			tries=0; /**This means tries are infinite*/
		}
		else{
			tries=(int)difficult*5;
		}
		this.difficult=difficult;
		ArrayCircle=new ArrayList<Circle>();
		createTest();
	}
	
	/**This new constructor is used in Bonus class*/
	/**06/02/2014->It's also for arcade mode*/
	public Test(double difficult, int tries){
		this.difficult=difficult;
		this.tries=tries;
		ArrayCircle=new ArrayList<Circle>();
		createTest();
	}
	
	private void createTest(){
	
		/**ARP-07/10/13: I will calculate first the move that the circles will follow. Then
		 * I'll create the case.
		 */
		int mov=random(4,1);
		int yPos=0,yPos2=0;
		double ySpeed=0,ySpeed2=0;
		
		double xSpeed=difficult;
		switch(mov){
		/**Parallel*/
		case 1:
			yPos=yPos2=random(8,6);
			break;
		/**To the top*/
		case 2:
			yPos=yPos2=random(7,6);
			ySpeed=ySpeed2=1;
			break;
		/**To the bottom*/
		case 3: 
			yPos=yPos2=random(8,8);
			ySpeed=ySpeed2=-1;
			break;
		/**One top, other down*/
		case 4: 
			mov=random(2,1);
			switch(mov){
			/**Big circle top, small bottom*/
			case 1:
				yPos=15;
				yPos2=1;
				ySpeed=-difficult;
				ySpeed2=difficult;
				xSpeed=difficult;
				break;
			/**Big circle bottom, small top*/
			case 2:
				yPos=1;
				yPos2=15;
				ySpeed=difficult;
				ySpeed2=-difficult;
				xSpeed=difficult;
				break;
			}
			break;
		}
		ArrayCircle.add(new Circle(-3,yPos,xSpeed,ySpeed));
		ArrayCircle.add(new Circle(11,yPos2,-xSpeed,ySpeed2));
	}

	/**ARP-30/10/13: This method generate a random number between 1 and 10. I use to get
	both coordinates (x and y) and the y coordinate of the position. I use mult to decided
	the max range of the random numbers, I use that because speed can't be very fast.*/
	public int random(int mult, int sum){
		Random rnd=new Random();
		return (int) (rnd.nextInt(mult)+sum);
	}
	public double getDifficult() {
		return difficult;
	}

	public void setDifficult(double difficult) {
		this.difficult = difficult;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public List<Circle> getArrayCircle() {
		return ArrayCircle;
	}

	public void setArrayCircle(List<Circle> arrayCircle) {
		ArrayCircle = arrayCircle;
	}
	public void newTest(){
		createTest();
	}
}
