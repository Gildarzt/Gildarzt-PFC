package pfc.game.presentation;

import pfc.game.presentation.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class ArcadeConf extends Activity{

	private EditText etSpeed;
	private EditText etTries;
	private CheckBox cbSpeed;
	private CheckBox cbBonus;
	private CheckBox cbSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arc_conf);
	}	
	
	@Override
	protected void onResume(){
		super.onResume();
		
		ImageButton btnArcAccept=(ImageButton)findViewById(R.id.aceptButton);
		btnArcAccept.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				etSpeed=(EditText)findViewById(R.id.speedET);
				etTries=(EditText)findViewById(R.id.tryET);
				cbSpeed=(CheckBox)findViewById(R.id.speedCB);
				cbBonus=(CheckBox)findViewById(R.id.bonusCB);
				cbSound=(CheckBox)findViewById(R.id.soundCB);
				
				int speed=Integer.parseInt(etSpeed.getText().toString());
				Intent intent=new Intent(ArcadeConf.this,ArcadeMode.class);
				if(speed>0)
					intent.putExtra("speed", speed);
				else
					intent.putExtra("speed", 1);
				intent.putExtra("tries", Integer.parseInt(etTries.getText().toString()));
				if(cbSpeed.isChecked())
					intent.putExtra("incSpeed","Si");
				else
					intent.putExtra("incSpeed", "No");
				if(cbBonus.isChecked())
					intent.putExtra("bonus","Si");
				else
					intent.putExtra("bonus", "No");
				if(cbSound.isChecked())
					intent.putExtra("sound","Si");
				else
					intent.putExtra("sound", "No");
				
				startActivityForResult(intent,1);
				
			}    	
	    });
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				Intent intent = getIntent();
			    setResult(RESULT_OK, intent);
			    finish();         
		    }
		}
	}//onActivityResult
	

}
