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

public class MainMenuScreen implements Screen {
	private int idPlayer;
	Game game;
	
	Screen gameScreen;
	
	/** The gui cam. */
	OrthographicCamera guiCam;
	
	/** Se utiliza para dibujar y optimizar las imagenes en el renderizado de la pantalla. */
	SpriteBatch batcher; 
	
	/** Boton start */
	BoundingBox startBounds;
	/** Difficult buttons */
	BoundingBox easyBounds;
	BoundingBox normalBounds;
	BoundingBox hardBounds;
	
	/**Boolean to control the difficults buttons*/
	boolean easy=false,normal=false,hard=false;
	int difficult=2;
	
	/** The touch point es un vector que recogera las coordenadas de la pulsacion. */
	Vector3 touchPoint;

	public MainMenuScreen(Game game,int idPlayer){
		this.game = game;
		this.idPlayer=idPlayer;
		guiCam = new OrthographicCamera(10, 15); //definicion de nuestra propia medida del juego
		guiCam.position.set(10f / 2, 15f / 2, 0); // Donde estara mirando la camara
		batcher = new SpriteBatch(); //crear solamente un batcher por pantalla y eliminarlo cuando no se use
		
		//definir area de la imagen para el boton de pulsado
		//para comprobar si se ha tocado la pantalla
		startBounds = new BoundingBox(new Vector3(2, 5, 0), new Vector3(8, 6, 0));
		easyBounds= new BoundingBox(new Vector3(0,2,0),new Vector3(3,4,0));
		normalBounds = new BoundingBox(new Vector3(3.5f, 2, 0), new Vector3(6.5f, 4, 0));
		hardBounds = new BoundingBox(new Vector3(6.5f,2,0), new Vector3(11,4,0));
		touchPoint = new Vector3();
		gameScreen = new GameScreen(game);	
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
			//si se presino start
			if(startBounds.contains(touchPoint)){
				((GameScreen) gameScreen).setDifficult(difficult,idPlayer);
				game.setScreen(gameScreen);
				return;
			}
			
			//if we touch a difficult button.
			if(easyBounds.contains(touchPoint) && normal==false && hard==false){
				easy=true;
				difficult=1;
			}
			else if(normalBounds.contains(touchPoint) && easy==false && hard==false){
				normal=true;
				difficult=2;
			}
			else if(hardBounds.contains(touchPoint) && easy==false && normal==false){
				hard=true;
				difficult=3;
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
		
		//Dibujando elementos en pantalla activamos el Blending
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.getTitle(), 1, 8, 8, 6);
		batcher.draw(Assets.getStart(), 2, 5, 6, 1);
		
		//This is to get the difficult.
		if(easy){
			batcher.draw(Assets.getEasyOn(), -1, 1, 5, 3);
			batcher.draw(Assets.getMediumOff(), 2.5f,1,5,3);
			batcher.draw(Assets.getHardOff(), 5.75f,1,5,3);
		}
		else if(normal){
			batcher.draw(Assets.getEasyOff(), -1, 1, 5, 3);
			batcher.draw(Assets.getMediumOn(), 2.5f,1,5,3);
			batcher.draw(Assets.getHardOff(), 5.75f,1,5,3);
		}
		else if(hard){
			batcher.draw(Assets.getEasyOff(), -1,1,5,3);
			batcher.draw(Assets.getMediumOff(), 2.5f,1,5,3);
			batcher.draw(Assets.getHardOn(), 5.75f,1,5,3);
		}
		else{
			batcher.draw(Assets.getEasyOff(), -1,1,5,3);
			batcher.draw(Assets.getMediumOff(),2.5f,1,5,3);
			batcher.draw(Assets.getHardOff(), 5.75f,1,5,3);		
		}
		
		
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
