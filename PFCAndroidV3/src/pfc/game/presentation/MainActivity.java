package pfc.game.presentation;

import pfc.game.presentation.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnHola=(Button)findViewById(R.id.mainButton);
		btnHola.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent=new Intent(MainActivity.this,SelectMode.class);
				startActivityForResult(intent,1);
			}    	
	    });
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
			    finish();         
		    }
		}
	}//onActivityResult
}

