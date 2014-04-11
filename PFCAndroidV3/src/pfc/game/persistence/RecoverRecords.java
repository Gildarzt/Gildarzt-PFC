package pfc.game.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import pfc.game.domain.Records;

/**ARP-26/01/2014: This class allows to get the player's personal records from the records.txt that create when
 * the game finish.
 * @author Antonio
 *
 */
public class RecoverRecords {
	private List<Records> recList;
	private List<Integer> goalList;
	private String recoverPool;
	
	public RecoverRecords(int idPlayer){
		recList=new ArrayList<Records>();
		goalList=new ArrayList<Integer>();
		recoverPool=parseFile(idPlayer);
		readPool();
	}
	
	private String parseFile(int idPlayer){
		String nomarchivo = idPlayer+"GaR.txt";
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
						todo = todo + linea + " ";
						linea = br.readLine();
					}
					br.close();
					archivo.close();
					file.delete();
					//dir.delete();
				} catch (IOException e) {
					System.out.println("Se ha producido un error al intentar leer el archivo");
				}
			}
		}
		return todo;
	}
	private void readPool(){
		String delim=" ";
		String[] tokens=recoverPool.split(delim);
		int i;
		for(i=1;i<tokens.length && !tokens[i].equals("Record");i++){
			System.out.println("estoy dentro del read pool");
			goalList.add(Integer.parseInt(tokens[i]));
		}
		for(i=i+1;i<tokens.length;i++){
			String[]aux=tokens[i].split(",");
			recList.add(new Records(Integer.parseInt(aux[0]),Integer.parseInt(aux[1])));
		}
	}
	
	public List<Records> getRecList() {
		return recList;
	}
	public void setRecList(List<Records> recList) {
		this.recList = recList;
	}
	public List<Integer> getGoalList() {
		return goalList;
	}
	public void setGoalList(List<Integer> goalList) {
		this.goalList = goalList;
	}
}
