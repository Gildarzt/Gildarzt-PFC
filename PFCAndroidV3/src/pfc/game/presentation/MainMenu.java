package pfc.game.presentation;

import pfc.game.domain.Goal;
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
import android.widget.ImageView;

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
		paintRewards();
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
	private void paintRewards(){
		ImageView reward1=(ImageView)findViewById(R.id.imageView1);
		reward1.setImageResource(ShowReward(1));
		ImageView reward2=(ImageView)findViewById(R.id.ImageView01);
		reward2.setImageResource(ShowReward(2));
		ImageView reward3=(ImageView)findViewById(R.id.ImageView02);
		reward3.setImageResource(ShowReward(3));
		ImageView reward4=(ImageView)findViewById(R.id.ImageView03);
		reward4.setImageResource(ShowReward(9));
		ImageView reward5=(ImageView)findViewById(R.id.ImageView04);
		reward5.setImageResource(ShowReward(12));
		ImageView reward6=(ImageView)findViewById(R.id.ImageView05);
		reward6.setImageResource(ShowReward(13));
	}
	private void UpdateDB(){
		RecoverRecords rec=new RecoverRecords(pla.getId());
		int i;
		boolean aux=false; //I use this variable to see if the player got the goal before.
		for(i=0;i<rec.getGoalList().size();i++){
			aux=false;
			for(int j=0;j<pla.getGoalList().size();j++)
				if(pla.getGoalList().get(j).getId()==rec.getGoalList().get(i))
					aux=true;
			if(!aux)
				agent.insertPlayerGoal(pla.getId(), rec.getGoalList().get(i), context);
		}
		for(i=0;i<rec.getRecList().size();i++){
			if(rec.getRecList().get(i).getId()==pla.getRecList().get(i).getId())
				if(rec.getRecList().get(i).getValue()>pla.getRecList().get(i).getValue())
					agent.insertPlayerRecord(pla.getId(), rec.getRecList().get(i).getId(),rec.getRecList().get(i).getValue(), context);
		}
		//if(rec.getGoalList().size()>0)
			pla.setGoalList(agent.ReadGoalFromPlayer(pla.getId(), context));
		if(rec.getRecList().size()>0 )
			pla.setRecList(agent.ReadRecFromPlayer(pla.getId(), context));
	}
	private void SendResults(){
		pla.sendReport(activity);
	}
	/**This method upload the reward image.*/
	private int ShowReward(int idRew){
		int res=0;
		for(int i=0;i<pla.getGoalList().size();i++){
			Goal goal=pla.getGoalList().get(i);
			if(goal.getIdRew()==idRew){
				switch(goal.getIdRew()){
				case 13:
					res=R.drawable.r13;
					break;
				case 12:
					res=R.drawable.r12;
					break;
				case 9:
					res=R.drawable.r9;
					break;
				case 3:
					res=R.drawable.r3;
					break;
				case 2:
					res=R.drawable.r2;
					break;
				case 1:
					res=R.drawable.r1;
				}
				
			}
		}
		return res;	
	}
}
