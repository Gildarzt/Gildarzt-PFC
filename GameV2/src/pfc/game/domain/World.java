package pfc.game.domain;

import java.util.Random;

import pfc.game.domain.Circle;
import pfc.game.domain.widgets.Click;
import pfc.game.persistence.Patient;

/*ARP-09/10/13: In this class I'm going to initialise the toy and the the circles.
I also to create a method that move the toy and the circles in the screen*/
public class World {
	private boolean touched;//That variable is used to see if the screen have been touch.
	private boolean bonusTime;
	private boolean arcMode;
	private Circle cSmall;
	private Circle cBig;
	private Test test;
	private Patient pat;
	private ArcadeMode arc;
	private int oldTries;
	private int bTime;
	private double difficult;
	private Click click;
	
	/**<--------------------------------------------CONSTRUCTORS------------------------------------------->*/
	public World(double difficult,int idPlayer,String namePlayer){
		this.test=new Test(difficult);
		this.cBig=null;
		this.cSmall=null;
		this.pat=new Patient(namePlayer,idPlayer,test.getTries());
		this.bTime=0;
		this.click=new Click();
		this.difficult=difficult;
		this.bonusTime=false;
		this.arcMode=false;
		
	}
	/**Bonus time constructor*/
	public World(World world, boolean bonusTime){
		this.test=new Test(world.getDifficult(),world.getbTime());
		this.cBig=null;
		this.cSmall=null;
		this.pat=world.getPatient();
		this.oldTries=world.getTest().getTries();
		this.bonusTime=bonusTime;
		this.difficult=world.getDifficult();
		this.arcMode=world.isArcMode();
		this.click=new Click(world.getClick());
		if(arcMode)
			this.arc=world.getArc();
		
	}
	/**This constructor is when you want to create a new world with things of old one.*/
	public World(World world){
		this.test=new Test(world.getDifficult(),world.getOldTries());
		this.cBig=null;
		this.cSmall=null;
		this.bTime=0;
		this.bonusTime=false;
		this.pat=world.getPatient();
		this.difficult=world.getDifficult();
		//Store the rights and wrongs clicks in each mode, normal and bonus.
		this.click=new Click(world.getClick());
		this.arcMode=world.isArcMode();
		if(arcMode)
			this.arc=world.getArc();
	}
	/**Arcade mode */
	public World(ArcadeMode arc) {
		// TODO Auto-generated constructor stub
		this.test=new Test(arc.getDifficult(),arc.getTries());
		this.cBig=null;
		this.cSmall=null;
		this.pat=new Patient("arcade",1,arc.getTries());
		this.bTime=0;
		this.click=new Click();
		this.difficult=arc.getDifficult();
		this.bonusTime=false;
		this.arc=arc;
		this.arcMode=true;
	}
	/**In this method I initialise the circles.*/
	public boolean initialize(){
		boolean res=false;
		if(test.getTries()>0){
			if(!arcMode){
				/**I will check now the goals and records to bonus time*/
				if(isBonusTime()){
					checkBGoals();
					updateBRecords();
				}
				if(test.getDifficult()==1 && click.getSuccessNormalClick()>=4)
					pat.getGoalList().add(1);
				else if(test.getDifficult()==2 && click.getSuccessNormalClick()>=8)
					pat.getGoalList().add(2);
			}
			test.setTries(test.getTries()-1);
			if(isArcMode()){
				if(arc.isSpeedInc()){
					setDifficult(getDifficult()+0.2);
					test.setDifficult(getDifficult());
				}
			}
			test.newTest();
			cBig=test.getArrayCircle().get(0);
			cSmall=test.getArrayCircle().get(1);
			test.getArrayCircle().clear();
			touched=false;
			
			res=true;
		}
		else{
			if(!arcMode){
				if(test.getTries()==0 && getDifficult()>=3 && !isBonusTime()){	
					if(pat.getLifes()>0){
						checkGoals();
						setDifficult(getDifficult()+0.2);
						test.setDifficult(getDifficult());
						test.newTest();
						cBig=test.getArrayCircle().get(0);
						cSmall=test.getArrayCircle().get(1);
						test.getArrayCircle().clear();
						touched=false;
						res=true;
					}
					else{
						if(!isBonusTime()){
							pat.saveResults(click);
							pat.saveGoalsAndRecords();
						}
					}
				}
				else{
					/**The game is over, I proceed to write and send the file with the answer if we were
					 * on normal game, if we are on bonus time I don't do anything.
					 */	
					if(!isBonusTime()){
						pat.saveResults(click);
						pat.saveGoalsAndRecords();
					}
				}	
			}/**Arcade mode is over*/
			else if(arcMode){
				pat.saveResults(click);
			}
		}
		return res;
	}
	
	public void updateRecords(){
		if(getPatient().getRecordList().size()>0){
			if(getPatient().getRecordList().get(0).getValue()<click.getSuccessNormalClick())
				getPatient().getRecordList().get(0).setValue(click.getSuccessNormalClick());
			if(getPatient().getRecordList().get(1).getValue()<click.getFailNormalClick())
				getPatient().getRecordList().get(1).setValue(click.getFailNormalClick());
		}
	}
	public void updateBRecords(){
		if(getPatient().getRecordList().size()>0){
			if(getPatient().getRecordList().get(2).getValue()<click.getSuccessBonusClick())
				getPatient().getRecordList().get(2).setValue(click.getSuccessBonusClick());
			if(getPatient().getRecordList().get(3).getValue()<click.getFailBonusClick())
				getPatient().getRecordList().get(3).setValue(click.getFailBonusClick());
		}
	}
	/**ARP-09/01/14: This method check if the goals of difficult and bonus time have been 
	 * completed*/
	private void checkGoals(){
		/**Switch to check if the right clicks gets a goal*/
		switch(click.getSuccessNormalClick()){
		case 5:
			pat.getGoalList().add(3);
			break;
		case 10:
			pat.getGoalList().add(4);
			break;
		case 15:
			pat.getGoalList().add(5);
			break;
		}
		/**Switch to check if the wrong clicks gets a goal*/
		switch(click.getFailNormalClick()){
		case 5:
			pat.getGoalList().add(6);
			break;
		case 10:
			pat.getGoalList().add(7);
			break;
		case 15:
			pat.getGoalList().add(8);
			break;
		}
		/**Switch to check if the time to bonus goals gets a goal*/
		switch(getbTime()){
		case 5:
			pat.getGoalList().add(9);
			break;
		case 10:
			pat.getGoalList().add(10);
			break;
		case 15:
			pat.getGoalList().add(11);
			break;
		}
	}
	
	private void checkBGoals(){
		/**Switch to check if the right clicks on bonus screen gets a goal*/
		switch(click.getSuccessBonusClick()){
		case 5:
			pat.getGoalList().add(13);
			break;
		case 10:
			pat.getGoalList().add(14);
			break;
		case 15:
			pat.getGoalList().add(15);
			break;
		}
		/**Switch to check if the wrong clicks on bonus screen gets a goal*/
		switch(click.getFailBonusClick()){
		case 5:
			pat.getGoalList().add(16);
			break;
		case 10:
			pat.getGoalList().add(17);
			break;
		case 15:
			pat.getGoalList().add(18);
			break;
		}
	}
	
	//ARP-15/10/13: This method return if the circles are in the screen or not.
	public boolean cGone(){
		boolean res=false;
		if(cBig!=null){
			if(getcBig().getPosition().x>=10){
				if(getcSmall().getPosition().x<=0){
					res=true;
				}
			}
		}
		else
			res=true;
		return res;
	}

	/**
	 * Update.
	 *
	 * @param delta: delta is the seconds time since the last render call
	 */
	public void update(float delta){
		cBig.getPosition().add(cBig.getDirection().x * delta, cBig.getDirection().y * delta, 0);
		cSmall.getPosition().add(cSmall.getDirection().x * delta, cSmall.getDirection().y * delta, 0);
	}

	/**ARP-30/10/13: This method generate a random number between 1 and 10. I use to get
	both coordinates (x and y) and the y coordinate of the position. I use multiplication to decided
	the max range of the random numbers, I use that because speed can't be very fast.*/
	public int random(int mult, int sum){
		Random rnd=new Random();
		return (int) (rnd.nextInt(mult)+sum);
	}
	
	/**ARP-17/12/13: this method check if the circles are in the screen or not, the difference
	 * between this one and the cGone method is that this is for the beginning movement instead
	 * of the final movement.
	 */
	public boolean cBegin(){
		boolean res=false;
		if(cBig!=null){
			if(getcBig().getPosition().x>=0){
				if(getcSmall().getPosition().x<=10){
					res=true;
				}
			}
		}
		return res;
	}
	
	/**<------------------------------------------------------------GETTERS AND SETTERS----------------------------------------------------------------------------->*/
	public double getDifficult() {
		return difficult;
	}
	public void setDifficult(double difficult) {
		this.difficult = difficult;
	}
	public Patient getPatient() {
		return pat;
	}
	public void setPatient(Patient pat) {
		this.pat = pat;
	}
	public boolean isTouched() {
		return touched;
	}
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}

	public Circle getcSmall() {
		return cSmall;
	}

	public void setcSmall(Circle cSmall) {
		this.cSmall = cSmall;
	}

	public Circle getcBig() {
		return cBig;
	}

	public void setcBig(Circle cBig) {
		this.cBig = cBig;
	}
	public int getbTime() {
		return bTime;
	}
	public void setbTime(int bTime) {
		this.bTime = bTime;
	}
	public boolean isBonusTime() {
		return bonusTime;
	}
	public void setBonusTime(boolean bonusTime) {
		this.bonusTime = bonusTime;
	}
	public int getOldTries() {
		return oldTries;
	}
	public void setOldTries(int oldTries) {
		this.oldTries = oldTries;
	}
	public ArcadeMode getArc() {
		return arc;
	}
	public void setArc(ArcadeMode arc) {
		this.arc = arc;
	}
	public boolean isArcMode() {
		return arcMode;
	}
	public void setArcMode(boolean arcMode) {
		this.arcMode = arcMode;
	}
	public Click getClick() {
		return click;
	}
	public void setClick(Click click) {
		this.click = click;
	}
}
