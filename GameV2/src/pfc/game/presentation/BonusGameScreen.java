package pfc.game.presentation;

import java.util.Timer;
import java.util.TimerTask;

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

/**ARP-11/12/13: this class shows the game in Bonus version.*/
public class BonusGameScreen implements Screen{
	static boolean debugC=false; //Show the correct message.
	static boolean debugI=false; //Show the incorrect message.
	Game game;
		
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	
	Vector3 touchPoint;
		
	World world;
	
	Timer timerC,timerI;
		
	public BonusGameScreen(Game game, World world){
		this.game = game;
		guiCam = new OrthographicCamera(10, 15);
		guiCam.position.set(10f / 2, 15f / 2, 0);
		batcher = new SpriteBatch();	
		touchPoint = new Vector3();
		this.world=new World(world,true);
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
		/**ARP-15/10/12: This if check if the circles are in the screen or not, if they aren't
		 * restart the circles again.
		 */
		if(world.cGone()){
			if(!world.isTouched()){
				if(world.cBegin()){
					world.getPatient().getResultList().add(new Result(1,world.getDifficult(),debugC,"Bonus Time"));
					failBonusClick();
				}
			}
			if(!world.initialize()){
				GameScreen gameScreen=new GameScreen(game,world);
				game.setScreen(gameScreen);
			}
		}
			
		if (Gdx.input.justTouched()){
			if(!world.isTouched()){
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				int x1=(int)world.getcBig().getPosition().x-2,
						y1=(int)world.getcBig().getPosition().y-2,
						x2=(int)world.getcBig().getPosition().x+2,
						y2=(int)world.getcBig().getPosition().y+2;
				BoundingBox aux=new BoundingBox(new Vector3(x1,y1,0),new Vector3(x2,y2,0));
				if(world.getcBig().equals(world.getcSmall()) && aux.contains(touchPoint)){
					successBonusClick();
				}
				else{
					failBonusClick();
				}
				if(!world.isArcMode())
					world.getPatient().getResultList().add(new Result(1,world.getDifficult(),debugC,"Bonus Time"));
			}
			world.setTouched(true);
			if(!world.isArcMode())
				world.updateBRecords();
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
		batcher.end();
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
	private void successBonusClick(){
		System.out.println("Bonus: Se han cruzado");
		debugC=true;
		if(!world.isArcMode()){
			world.getPatient().setcTimes(world.getPatient().getcTimes()+1);
			world.getClick().setSuccessBonusClick(world.getClick().getSuccessBonusClick()+1);
			world.getClick().setFailBonusClick(0);;
			world.getClick().setTotalSuccessClick(world.getClick().getTotalSuccessClick()+1);
			if(world.getPatient().getcTimes()==5 && world.getPatient().getLifes()<5){
				world.getPatient().setLifes(world.getPatient().getLifes()+1);
				world.getPatient().setcTimes(0);
			}
			else if(world.getPatient().getLifes()==5){
				world.getPatient().getGoalList().add(12);
			}
		}
	}
	private void failBonusClick(){
		System.out.println("Bonus Time, has fallado, intentalo otra vez!");
		debugI=true;
		world.setbTime(0);
		if(!world.isArcMode()){
			world.getPatient().setcTimes(0);
			world.getClick().setSuccessBonusClick(world.getClick().getSuccessBonusClick()+1);
			world.getClick().setFailBonusClick(0);
			world.updateBRecords();
		}
	}
}
