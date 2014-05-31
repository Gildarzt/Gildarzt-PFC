package pfc.game.presentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
	private String rValue,fValue,rSValue,fSValue,iDifficult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arc_result);
		
		getData();
		TextView nRightsValue=(TextView)findViewById(R.id.nRightsValue);
		nRightsValue.setText(rValue);
		TextView nFailsValue=(TextView)findViewById(R.id.nFailsValue);
		nFailsValue.setText(fValue);
		TextView nSRightValue=(TextView)findViewById(R.id.nSpreeRightsValue);
		nSRightValue.setText(rSValue);
		TextView nSFailValue=(TextView)findViewById(R.id.nSpreeFailsValue);
		nSFailValue.setText(fSValue);
		TextView Idifficult=(TextView)findViewById(R.id.initialDifficultValue);
		Idifficult.setText(iDifficult);
		
		
	}	
	
	@Override
	public void onResume(){
		super.onResume();
		
		ImageButton btnAcept=(ImageButton)findViewById(R.id.aceptButton);
		btnAcept.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				Intent intent=new Intent(ArcResult.this,ArcadeMode.class);
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
	/**12/04/14: this method check if there is a txt file in the directory and show the result*/
	private String getResultTest(){
		String nomarchivo = "arcade.txt";
		String todo="";
		File root = Environment.getExternalStorageDirectory();
		if(root.canRead()){
			File dir = new File(root + "/pfc");
			File file=new File(dir,nomarchivo);
			
			if(dir.exists() && file.exists()){
				try {
					FileInputStream fIn = new FileInputStream(file);
					InputStreamReader archivo = new InputStreamReader(fIn);
					BufferedReader br = new BufferedReader(archivo);
					String linea = br.readLine();
					while (linea != null) {
						todo = todo + linea + "\n";
						linea = br.readLine();
					}
					br.close();
					archivo.close();
					file.delete();
				} catch (IOException e) {
					System.out.println("Se ha producido un error al intentar leer el archivo");
				}
			}
		}
		return todo;
	}
	private void getData(){
		String data=getResultTest();
		String delim="\n";
		String[] tokens=data.split(delim);
		int i=0;
		for(;i<tokens.length;i++){
			String[]aux=tokens[i].split(" ");
			switch (i){
			case 1:
				char auxChar=aux[1].charAt(0);
				iDifficult=Character.toString(auxChar);
				break;
			case 2:
				rValue=aux[1];
				break;
			case 3:
				fValue=aux[1];
				break;
			case 4:
				rSValue=aux[1];
				break;
			case 5:
				fSValue=aux[1];
				break;
			}
		}
	}
}
