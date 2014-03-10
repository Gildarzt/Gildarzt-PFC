package pfc.game.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import pfc.game.connection.HttpClientManager;
import pfc.game.connection.HttpClientManager.OnExecuteHttpPostAsyncListener;
import pfc.game.domain.Player;
import android.app.Activity;
import android.os.Environment;

/**ARP-08/02/2014: This class is used to create the result file before play the game. Here I add the data of the
 * patient.
 * @author Antonio
 *
 */
public class FileResult {
	private HttpClientManager httpclient;
	private String recoveryPool;
	private Player pla;
	private boolean res;
	public OnExecuteAsyncPostListener listenerAsyncPost;//Interface
	private static String KEY_SUCCESS = "success";
	private String pattern;//This variable is used on the sendAllReports method.
	
	public FileResult(Activity activity, Player pla){
		httpclient = new HttpClientManager(activity);
		this.pla=pla;
		res=false;
	}
	
	public boolean createFile(){
		res=false;
		FileWriter file=null;
		String name=pla.getName()+pla.getId()+".txt";
		File root=Environment.getExternalStorageDirectory();
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			File datafile=new File(dir,name);
		
			if(!dir.exists())
				dir.mkdir();
			if(!datafile.exists()){
				try {
					datafile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				File[] aux=dir.listFiles();
				name=aux.length+"_"+name;
				datafile=new File(dir,name);
			}
			try{
				file = new FileWriter(datafile);
				BufferedWriter out=new BufferedWriter(file);
				out.write("Code "+pla.getPsiCode()+"\n");
				out.write("Name: "+pla.getName()+"\n");
				out.write("Surname: "+pla.getSurname()+"\n");
				res=true;
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != file)
						file.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return res;
	}
	public void SendAllReports(){
		pattern=pla.getName()+pla.getId();
		File root=Environment.getExternalStorageDirectory();
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			String[] dirList=dir.list(new FilenameFilter(){
				@Override
				public boolean accept(File dir, String fileName) {
					// TODO Auto-generated method stub
					return fileName.contains(pattern);
				}
			});
			if(dirList!=null)
				for(String fileName:dirList){
					if(SendReport(fileName)){
						System.out.println("Se ha enviado correctamente");
					}
					else
						System.out.println("Ha ocurrido un error");
				}
		}
	}
	/**ARP-02/03/2014 in this method I will create the report using the txt file that the game created when finish
	 * This method have to be called for each report.
	 * @return res
	 */
	private boolean SendReport(String fileName){
		res=false;
		recoveryPool=parseFile(fileName);
		String delim="\n";
		String[] tokens=recoveryPool.split(delim);
		int i=0;
		for(;i<tokens.length;i++){
			String[]aux=tokens[i].split(" ");
			System.out.println(aux.toString());
			httpclient.addNameValue(aux[0], aux[1]);
			if(aux.length==6){
				httpclient.addNameValue(aux[2], aux[3]);
				httpclient.addNameValue(aux[4], aux[5]);
			}
		}
		
		httpclient.setOnExecuteHttpPostAsyncListener(new OnExecuteHttpPostAsyncListener(){
			@Override
			public void onExecuteHttpPostAsyncListener(String ResponseBody) {
				try{
					JSONObject json = new JSONObject(ResponseBody);
				    if (json.getString(KEY_SUCCESS) != null) {
				    	if((Integer.parseInt(json.getString(KEY_SUCCESS)) == 1)){
				    		if(json.has("report")){
				    			parseJSONReport(json.getJSONObject("report"));
				    		}
				    		res=true;
				    		listenerAsyncPost.onFinishAsyncPost("Se ha guardado correctamente el libro");
				    	} else {
				    		listenerAsyncPost.onErrorAsyncPost("Se ha producido un error");
				    	}
				    }else{
				    	listenerAsyncPost.onErrorAsyncPost("Se ha producido un error");
				    }
				}catch (JSONException e) {      
					listenerAsyncPost.onErrorAsyncPost("Se ha producido un error");
			    }   
			}
			
			@Override
		    public void onErrorHttpPostAsyncListener(String message) {
				listenerAsyncPost.onErrorAsyncPost("Se ha producido un error " + message);
		    }
		});      
		httpclient.executeHttpPost("http://192.168.1.14:8080/PFCServer/Listener");
		return res;
	}
	
	private String parseFile(String fileName){
		String nomarchivo = fileName;
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
					//file.delete();
					//dir.delete();
				} catch (IOException e) {
					System.out.println("Se ha producido un error al intentar leer el archivo");
				}
			}
		}
		return todo;
	}
	public interface OnExecuteAsyncPostListener{ 
		void onFinishAsyncPost(String message); 
		void onErrorAsyncPost(String message); 
	}
	public void setOnExecuteAsyncPostListener(OnExecuteAsyncPostListener l){
		listenerAsyncPost = l;
	}
	private void parseJSONReport(JSONObject jsonReport) throws JSONException{
		  
	}
}
