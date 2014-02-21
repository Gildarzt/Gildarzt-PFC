package pfc.game.presentation;

import java.util.Timer;
import java.util.TimerTask;

import pfc.game.domain.ArcadeMode;
import pfc.game.domain.Assets;
import pfc.game.domain.Circle;
import pfc.game.domain.Text;
import pfc.game.domain.World;
import pfc.game.persistence.Result;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class GameScreen implements Screen{
	
	private boolean debugC=false; //Show the correct message.
	private boolean debugI=false; //Show the incorrect message.
	private boolean sleep=false;
    private Game game;
    private boolean arcade;
    private ArcadeMode arc;
	
	private OrthographicCamera guiCam;
	private SpriteBatch batcher;
	
	private Vector3 touchPoint;
	private World world;
	private BoundingBox bonusBounds;
	private Timer timerC;
	private Timer timerI;
	private Timer timerArc;
	
	public GameScreen(Game game){
		arcade=false;
		Initialize(game);
	}
	public GameScreen(Game game,World world){
		this.world=new World(world);
		Initialize(game);
	}
	
	public GameScreen(Game game, ArcadeMode arc) {
		// TODO Auto-generated constructor stub
		arcade=true;
		this.arc=arc;
		Initialize(game);
		this.world=new World(arc);
		timerArc=new Timer();
		sleep=true;
	}
	
	private void Initialize(Game game){
		this.game = game;
		guiCam = new OrthographicCamera(10, 15);
		guiCam.position.set(10f / 2, 15f / 2, 0);
		batcher = new SpriteBatch();
		bonusBounds=new BoundingBox(new Vector3(8,13,0),new Vector3(10,15,0));
		touchPoint = new Vector3();
		timerC=new Timer();
		timerI=new Timer();
	}
	@Override
	public void dispose() {
		batcher.dispose();	
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
	
	}

	
	@Override
	public void render(float delta){
		Circle cBig,cSmall;
		/**ARP-13/11/13: This timer manage the time that the feedback of correct message 
		 * appears on the screen.
		 */
		 
		TimerTask timerTaskC = new TimerTask(){ 
			public void run(){ 
				debugC=false;
	        } 
	    }; 
		/**ARP-13/11/13: This timer manage the time that the feedback of incorrect message 
		 * appears on the screen.
		 */
		TimerTask timerTaskI = new TimerTask(){ 
			public void run(){ 
				debugI=false;
	        } 
	    }; 
	    
	    TimerTask timerTaskArc=new TimerTask(){
	    	public void run(){
	    		sleep=false;
	    	}
	    };
	    
	    if(!sleep){
	    	/**ARP-15/10/12: This if check if the circles are in the screen or not, if they aren't
	    	 * restart the circles again.
	    	 */
	    	if(world.cGone()){
	    		/**This else is when player doesn't pulse the screen*/
	    		if(!world.isTouched()){
	    			if(world.cBegin()){
	    				System.out.println("No pulsado: Has fallado, intentalo otra vez!");
	    				debugI=true;
	    				world.setbTime(0);
	    				if(!world.isArcMode()){
	    					world.setbClick(world.getbClick()+1);
	    					world.getPatient().getResultList().add(new Result(1,world.getDifficult(),debugC,"Normal"));
	    					if(world.getDifficult()>=3){
	    						/**I put rights clicks to 0 because i want to know the number of consecutive clicks*/
	    						world.setgClick(0);
	    						world.getPatient().setLifes(world.getPatient().getLifes()-1);
	    						world.updateRecords();
	    					}
	    				}
	    			}
	    		}
	    		if(!world.initialize()){
	    			System.exit(0);
	    		}
	    	}
	    	
	    	if (Gdx.input.justTouched()){
	    		guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
	    		if(world.getbTime()>=3 && bonusBounds.contains(touchPoint)){
	    			if(!world.isArcMode())
	    				world.getPatient().setbTimes(world.getPatient().getbTimes()+world.getbTime());
	    			BonusGameScreen bonusScreen=new BonusGameScreen(game,world);
	    			game.setScreen(bonusScreen);
	    		}
	    		else if(!world.isTouched()){	
	    			int x1=(int)world.getcBig().getPosition().x-2,
	    					y1=(int)world.getcBig().getPosition().y-2,
	    					x2=(int)world.getcBig().getPosition().x+2,
	    					y2=(int)world.getcBig().getPosition().y+2;
	    			BoundingBox aux=new BoundingBox(new Vector3(x1,y1,0),new Vector3(x2,y2,0));
	    			if(world.getcBig().equals(world.getcSmall()) && aux.contains(touchPoint)){
	    				System.out.println("Se han cruzado");
	    				debugC=true;
	    				world.setbTime(world.getbTime()+1);
	    				if(!world.isArcMode()){
	    					world.setgClick(world.getgClick()+1);
	    					if(world.getDifficult()>=3){
	    						world.setbClick(0);
	    						world.updateRecords();
	    					}
	    				}
	    			}
	    			else{
	    				System.out.println("Has fallado, intentalo otra vez!");
	    				debugI=true;
	    				world.setbTime(0);
	    				if(!world.isArcMode()){
	    					world.setbClick(world.getbClick()+1);
	    					if(world.getDifficult()>=3){
	    						world.setgClick(0);
	    						world.getPatient().setLifes(world.getPatient().getLifes()-1);
	    						world.updateRecords();
	    					}
	    				}
	    			}
	    			if(!world.isArcMode()){
	    				if(world.getbTime()>=3)
	    					world.getPatient().getResultList().add(new Result(1,world.getDifficult(),debugC,"Normal, Bonus On"));
	    				else
	    					world.getPatient().getResultList().add(new Result(1,world.getDifficult(),debugC,"Normal"));
	    			}
	    		}
	    		world.setTouched(true);
	    	}
	    	world.update(delta);
		
	    	GL10 gl = Gdx.graphics.getGL10();
	    	gl.glClearColor(0,1,0,1);
	    	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		
	    	guiCam.update();
	    	batcher.setProjectionMatrix(guiCam.combined);
		
	    	batcher.disableBlending();
	    	batcher.begin();
	    	batcher.draw(Assets.getGameBackground(), 0, 0, 10, 15);
	    	batcher.end();
		
	    	cBig = world.getcBig();
	    	cSmall = world.getcSmall();
	    	batcher.begin();
	    	batcher.enableBlending();
	    	batcher.draw(Assets.getRec1(), cBig.getPosition().x, cBig.getPosition().y, 1f, 1f);
	    	batcher.draw(Assets.getRec2(), cSmall.getPosition().x, cSmall.getPosition().y,1.5f,1.5f);
	    	if(!world.isArcMode()){
	    		for(int a=0;a<world.getPatient().getLifes();a++)
	    			batcher.draw(Assets.getCorMsg(),a,14,0.5f,0.5f);
	    	}
	    	if(debugC){
	    		Text feedback=new Text(-1,6);
	    		batcher.draw(Assets.getCorMsg(), feedback.getPosition().x, feedback.getPosition().y, 12, 5f);
	    		timerC.schedule(timerTaskC, 500);	
	    	}
	    	
	    	if(debugI){
	    		Text feedback=new Text(-1,6);
	    		batcher.draw(Assets.getUncMsg(), feedback.getPosition().x, feedback.getPosition().y, 12, 5f);
	    		timerI.schedule(timerTaskI, 500);
	    	}
	    	if(!world.isArcMode()){
	    		if(world.getbTime()>=3 && world.getDifficult()>=3){
	    			batcher.draw(Assets.getCorMsg(),8,13,2,2);
	    		}
	    	}else{
	    		if(world.getArc().isBonus()){
	    			if(world.getbTime()>=3){
	    				batcher.draw(Assets.getCorMsg(),8,13,2,2);
	    			}
	    		}
	    	}
	    	batcher.end();
	    }
	    else{
	    	timerArc.schedule(timerTaskArc, 1000);
	    }
	}


	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void setDifficult(int difficult,int idPlayer){
		world = new World(difficult,idPlayer);
	}

}
