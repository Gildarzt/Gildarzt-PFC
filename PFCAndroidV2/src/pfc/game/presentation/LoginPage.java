package pfc.game.presentation;

import pfc.game.domain.Player;
import pfc.game.domain.R;
import pfc.game.persistence.Agent;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginPage extends Activity{
	
	private Agent agent;
	private EditText etName;
	private EditText etPass;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		agent=new Agent();
		context=this;
		
		Button btnReg=(Button)findViewById(R.id.regButton);
		btnReg.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent=new Intent(LoginPage.this,RegisterPage.class);
				startActivityForResult(intent,1);
			}    	
	    });
		Button btnLogin=(Button)findViewById(R.id.loginButton);
		
		btnLogin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				etName=(EditText)findViewById(R.id.editTextNick);
				etPass=(EditText)findViewById(R.id.editTextPass);
				int id=checkPlayer(etName.getText().toString(),etPass.getText().toString());
				if(id!=0){
					Player pla=agent.ReadPlayerlDB(id, context);
					pla.setGoalList(agent.ReadGoalFromPlayer(id, context));
					pla.setRecList(agent.ReadRecFromPlayer(id, context));
					Intent intent=new Intent(LoginPage.this,MainMenu.class);
					intent.putExtra("Player", pla);
					startActivityForResult(intent,1);
				}
				else{
					//jugador no registrado.
				}
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
	
	private int checkPlayer(String nick,String password){
		return agent.playerExist(nick, password,context);
	}

}
