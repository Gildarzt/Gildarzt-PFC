package pfc.game.presentation;

import pfc.game.domain.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectMode extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selected_mode);
	}	
	
	@Override
	public void onResume(){
		super.onResume();
		Button btnArc=(Button)findViewById(R.id.ArcadeMode);
		btnArc.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent=new Intent(SelectMode.this,ArcadeTip.class);
				startActivityForResult(intent,1);
			}    	
	    });
		
		Button btnHistory=(Button)findViewById(R.id.HistoryMode);
		btnHistory.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
					Intent intent=new Intent(SelectMode.this,LoginPage.class);
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
