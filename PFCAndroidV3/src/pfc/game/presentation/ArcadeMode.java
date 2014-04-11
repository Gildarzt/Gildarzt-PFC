package pfc.game.presentation;

import pfc.game.domain.Arcade;
import pfc.game.presentation.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ArcadeMode  extends Activity{
	private int speed,tries;
	private boolean incSpeed,bonus,sound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arcade_mode);
		
		TextView speedTV=(TextView)findViewById(R.id.speedValue);
		TextView sIncTV=(TextView)findViewById(R.id.incSpeedValue);
		TextView triesTV=(TextView)findViewById(R.id.tryValue);
		TextView bonusTV=(TextView)findViewById(R.id.bonusValue);
		TextView soundTV=(TextView)findViewById(R.id.soundValue);
		
		speed=getIntent().getParcelableExtra("speed");
		if(speed!=0){
			tries=getIntent().getParcelableExtra("tries");
			String incSpeedAux=getIntent().getParcelableExtra("incSpeed").toString();
			if(incSpeedAux.equals("No"))
				incSpeed=false;
			else 
				incSpeed=true;
			String bonusAux=getIntent().getParcelableExtra("bonus").toString();
			if(bonusAux.equals("No"))
				bonus=false;
			else 
				bonus=true;
			String soundAux=getIntent().getParcelableExtra("sound").toString();
			if(soundAux.equals("No"))
				sound=false;
			else 
				sound=true;
			speedTV.setText(speed);
			sIncTV.setText(incSpeedAux);
			triesTV.setText(tries);
			bonusTV.setText(bonusAux);
			soundTV.setText(soundAux);
			
		}else{
			speed=Integer.parseInt(speedTV.getText().toString());
			if(sIncTV.getText().toString().equals("No"))
				incSpeed=false;
			else
				incSpeed=true;
			tries=Integer.parseInt(triesTV.getText().toString());
			if(bonusTV.getText().toString().equals("No"))
				bonus=false;
			else
				bonus=true;
			if(soundTV.getText().toString().equals("No"))
				sound=false;
			else
				sound=true;
		}
	}	
	
	@Override
	public void onResume(){
		super.onResume();
		Button btnArc=(Button)findViewById(R.id.playButton);
		btnArc.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Arcade arcade=new Arcade(speed,tries,incSpeed,bonus,sound);
				Intent intent=new Intent(ArcadeMode.this,GamePage.class);
				intent.putExtra("arcade", arcade);
				startActivityForResult(intent,1);
			}    	
	    });
		
		Button btnHistory=(Button)findViewById(R.id.confButton);
		btnHistory.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
					Intent intent=new Intent(ArcadeMode.this,ArcadeConf.class);
					startActivityForResult(intent,1);
			}    	
	    });
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        Intent intent = getIntent();
	        setResult(RESULT_OK, intent);
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}


}
