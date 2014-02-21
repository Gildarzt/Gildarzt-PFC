package pfc.game.presentation;

import pfc.game.domain.Arcade;
import pfc.game.domain.Game1;
import pfc.game.domain.Player;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class GamePage extends AndroidApplication{
	private Player pla;
	private Arcade arc;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        arc=getIntent().getParcelableExtra("arcade");
	        
	        if(arc!=null){
	        	initialize(new Game1(arc.getDificult(),arc.getTries(),arc.isIncSpeed(),arc.isBonus(),arc.isSound()),false);
	        }
	        else{
	        	pla=getIntent().getParcelableExtra("Player");
	        	pla.createResultFile();
	        	initialize(new Game1(pla.getId()),false);
	        }
	 }
}
