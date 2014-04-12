package pfc.game.presentation;

import pfc.game.domain.Player;
import pfc.game.presentation.R;
import pfc.game.persistence.Agent;
import pfc.game.persistence.RecoverRecords;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainMenu extends Activity{
	private Player pla;
	private Agent agent;
	private Context context;
	private Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		activity=this;
		context=this;
		pla=getIntent().getParcelableExtra("Player");
		agent=new Agent();
		
		UpdateDB();
		SendResults();
	}	
	
	@Override
	public void onResume(){
		super.onResume();
		ImageButton btnReg=(ImageButton)findViewById(R.id.playButton);
		btnReg.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent=new Intent(MainMenu.this,GamePage.class);
				intent.putExtra("Player", pla);
				intent.putExtra("arcade", false);
				startActivity(intent);	
			}    	
	    });
		
		ImageButton btnLogin=(ImageButton)findViewById(R.id.profileButton);
		btnLogin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
					Intent intent=new Intent(MainMenu.this,ProfilePage.class);
					intent.putExtra("Player", pla);
					startActivity(intent);
					UpdateDB();
					//SendResults();
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
	
	private void UpdateDB(){
		RecoverRecords rec=new RecoverRecords(pla.getId());
		int i;
		for(i=0;i<rec.getGoalList().size();i++){
			agent.insertPlayerGoal(pla.getId(), rec.getGoalList().get(i), context);
		}
		for(i=0;i<rec.getRecList().size();i++){
			agent.insertPlayerRecord(pla.getId(), rec.getRecList().get(i).getId(),rec.getRecList().get(i).getValue(), context);
		}
		if(rec.getGoalList().size()>0)
			pla.setGoalList(agent.ReadGoalFromPlayer(pla.getId(), context));
		if(rec.getRecList().size()>0 )
			pla.setRecList(agent.ReadRecFromPlayer(pla.getId(), context));
	}
	private void SendResults(){
		pla.sendReport(activity);
	}
}
