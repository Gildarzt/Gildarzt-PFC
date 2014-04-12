package pfc.game.presentation;

import java.io.File;
import java.io.FilenameFilter;

import pfc.game.domain.Arcade;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ArcResult extends Activity{
	private int speed,tries;
	private boolean incSpeed,bonus,sound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arc_result);
		
		
	}	
	
	@Override
	public void onResume(){
		super.onResume();
		
			
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
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			String[] dirList=dir.list(new FilenameFilter(){
				@Override
				public boolean accept(File dir, String fileName) {
					// TODO Auto-generated method stub
					return fileName.contains("arcade");
				}
			});
			if(dirList!=null)
				res=true;
		}
		return res;
	}

}
