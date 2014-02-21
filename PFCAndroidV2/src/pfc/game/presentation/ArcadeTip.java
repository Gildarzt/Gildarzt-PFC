package pfc.game.presentation;

import pfc.game.domain.Arcade;
import pfc.game.domain.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ArcadeTip extends Activity{

	private EditText etSpeed;
	private EditText etTries;
	private CheckBox cbSpeed;
	private CheckBox cbBonus;
	private Arcade arcade;
	private CheckBox cbSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arcade_mode);
	}	
	
	@Override
	protected void onResume(){
		super.onResume();
		
		Button btnArcPlay=(Button)findViewById(R.id.arcPlay);
		
		btnArcPlay.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				etSpeed=(EditText)findViewById(R.id.etSpeed);
				etTries=(EditText)findViewById(R.id.etTries);
				cbSpeed=(CheckBox)findViewById(R.id.arcadeSpeedCB);
				cbBonus=(CheckBox)findViewById(R.id.arcadeBonus);
				cbSound=(CheckBox)findViewById(R.id.sound);
				int difficult=Integer.parseInt(etSpeed.getText().toString());
				int tries=Integer.parseInt(etTries.getText().toString());
				arcade=new Arcade(difficult,tries,cbSpeed.isChecked(),cbBonus.isChecked(),cbSound.isChecked());
				
				Intent intent=new Intent(ArcadeTip.this,GamePage.class);
				intent.putExtra("arcade", arcade);
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
