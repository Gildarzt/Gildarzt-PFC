package pfc.game.presentation;
import pfc.game.domain.Assets;
import pfc.game.presentation.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * 
 * @author Antonio
 *08/12/2013: This class is the result screen that appear after finish the minigame.
 */
public class ResultScreen implements Screen {	
	Game game;
	Screen gameScreen;
	OrthographicCamera guiCam;
	SpriteBatch batcher; 
		
	/**Button to finish the game*/
	BoundingBox finishBounds;
			
	/** The touch point es un vector que recogera las coordenadas de la pulsacion. */
	Vector3 touchPoint;

	public ResultScreen(Game game){
		this.game = game;
		guiCam = new OrthographicCamera(10, 15); 
		guiCam.position.set(10f / 2, 15f / 2, 0); 
		batcher = new SpriteBatch(); 
			
		finishBounds = new BoundingBox(new Vector3(2, 1, 0), new Vector3(8, 6, 0));
		touchPoint = new Vector3();
		gameScreen = new GameScreen(game);	
		
		/**09/01/14 Now I will check if someone goal have been got. I'll upload the
		 * new records too.
		 */
		
		
	}

	@Override
	public void dispose() {
		batcher.dispose();		
	}
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
			
		if (Gdx.input.justTouched()){
			System.out.println(Gdx.input.getX());
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			//if we touch finish button.
			if(finishBounds.contains(touchPoint)){
				/**This method will send the results to web page.*/
				//sendResults();
				return;
			}
		}
					
		GL10 gl = Gdx.graphics.getGL10(); //referencia a OpenGL 1.0
		gl.glClearColor(0,1,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
			
		//Dibujando el Background
		batcher.disableBlending(); //se elimina graficamente la transparencia ya que es un fondo
		batcher.begin();
		batcher.draw(Assets.getMainBackground(), 0, 0, 10, 15);
		batcher.end();
			
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.getTitle(), 1, 8, 8, 6);
		batcher.draw(Assets.getStart(), 2, 1, 6, 1);
		batcher.end();
			
	}


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
			
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
			
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
			
	}
}
