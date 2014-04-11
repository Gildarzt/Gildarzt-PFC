package pfc.game.presentation;

import pfc.game.domain.Player;
import pfc.game.presentation.R;
import pfc.game.persistence.Agent;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterPage extends Activity{
	
	private Agent agent;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		context=this;
		agent=new Agent();
		Button btnRegister=(Button)findViewById(R.id.regButton);
		btnRegister.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				if(CreatePlayer()){
					System.out.println("Jugador creado");
				}
				else 
					System.out.println("Fallo en la creación del jugador");
				Intent intent=new Intent(RegisterPage.this,LoginPage.class);
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
	public boolean CreatePlayer(){
		EditText etName=(EditText)findViewById(R.id.regNameET);
		EditText etSurname=(EditText)findViewById(R.id.regSurnameET);
		EditText etNick=(EditText)findViewById(R.id.regNickET);
		EditText etPass=(EditText)findViewById(R.id.regPasswordET);
		EditText etPsiName=(EditText)findViewById(R.id.psiCodeET);
			
		Player pla=new Player(agent.GetLastIdPlayer(context)+1,etName.getText().toString(),
								etSurname.getText().toString(),
								etNick.getText().toString(),
								etPass.getText().toString(),
								etPsiName.getText().toString());
		
		agent.InsertRecordInitialData(pla.getId(), context);
		
		return agent.insertPlayer(pla,context);
	}
}
