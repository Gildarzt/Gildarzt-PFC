package pfc.game.presentation;

import java.io.File;
import pfc.game.domain.Arcade;
import pfc.game.presentation.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
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
		
		speed=getIntent().getIntExtra("speed", 0);
		if(speed!=0){
			tries=getIntent().getIntExtra("tries", 3);
			String incSpeedAux=getIntent().getStringExtra("incSpeed").toString();
			if(incSpeedAux.equals("No"))
				incSpeed=false;
			else 
				incSpeed=true;
			String bonusAux=getIntent().getStringExtra("bonus").toString();
			if(bonusAux.equals("No"))
				bonus=false;
			else 
				bonus=true;
			String soundAux=getIntent().getStringExtra("sound").toString();
			if(soundAux.equals("No"))
				sound=false;
			else 
				sound=true;
			speedTV.setText(Integer.toString(speed));
			sIncTV.setText(incSpeedAux);
			triesTV.setText(Integer.toString(tries));
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
		ImageButton btnPlay=(ImageButton)findViewById(R.id.playButton);
		btnPlay.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Arcade arcade=new Arcade(speed,tries,incSpeed,bonus,sound);
				Intent intent=new Intent(ArcadeMode.this,GamePage.class);
				intent.putExtra("arcade", arcade);
				startActivityForResult(intent,1);
			}    	
	    });
		
		ImageButton btnConf=(ImageButton)findViewById(R.id.confButton);
		btnConf.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
					Intent intent=new Intent(ArcadeMode.this,ArcadeConf.class);
					startActivityForResult(intent,1);
			}    	
	    });
		
		if(checkResultTest()){
			Intent auxInt=new Intent(ArcadeMode.this,ArcResult.class);
			startActivityForResult(auxInt,1);
		}
			
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
	/**12/04/14: this method check if there is a txt file in the directory and show the result*/
	private boolean checkResultTest(){
		boolean res=false;
		File root=Environment.getExternalStorageDirectory();
		if(root.canRead()){
			File dir = new File(root + "/pfc");
			File file=new File(dir,"arcade.txt");
			if(dir.exists() && file.exists())
				res=true;
		}
		return res;
	}
}
