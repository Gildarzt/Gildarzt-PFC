package pfc.game.domain;

import pfc.game.presentation.GameScreen;
import pfc.game.presentation.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Game1 extends Game {
	private int idPlayer;
	private String namePlayer;
	private Screen mainMenu;
	private boolean arcade;
	private ArcadeMode arc;
	
	public Game1(int idPlayer,String namePlayer){
		this.idPlayer=idPlayer;
		this.namePlayer=namePlayer;
	}
	public Game1(){
		
	}
	/**This constructor is used for arcade mode*/
	public Game1(int difficult, int tries, boolean speedInc, boolean bonus,boolean sound) {
		// TODO Auto-generated constructor stub
		arcade=true;
		arc=new ArcadeMode(difficult,tries,speedInc,bonus,sound);
		
	}
	public void setMainMenu(Screen mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	public void create() {
		Assets.setAtlas("data/pack");; //cargar recursos
		if(arcade){
			if(arc.isSound()){
				Assets.getMusic().play();	  
				Assets.getMusic().setVolume(0.2f);
			}
			mainMenu=new GameScreen(this,arc);
		}
		else{
			mainMenu = new MainMenuScreen(this,idPlayer,namePlayer); //cargar Main Menu Screen
			Assets.getMusic().play();
			Assets.getMusic().setLooping(true);
			Assets.getMusic().setVolume(0.2f);
		}
		setScreen(mainMenu);
	}
}
