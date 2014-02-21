package pfc.game.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pfc.game.domain.Player;
import android.os.Environment;

/**ARP-08/02/2014: This class is used to create the result file before play the game. Here I add the data of the
 * patient.
 * @author Antonio
 *
 */
public class FileResult {
	
	public FileResult(){
		
	}
	
	public boolean createFile(Player pla){
		boolean res=false;
		FileWriter file=null;
		String name=pla.getId()+".txt";
		File root=Environment.getExternalStorageDirectory();
		if(root.canWrite()){
			File dir=new File(root+"/pfc");
			File datafile=new File(dir,name);
		
			if(!dir.exists())
				dir.mkdir();
			if(!datafile.exists())
			try {
				datafile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				file = new FileWriter(datafile);
				BufferedWriter out=new BufferedWriter(file);
				out.write("Codigo del psicologo "+pla.getPsiCode());
				out.write("Nombre paciente: "+pla.getName()+"\n");
				out.write("Apellidos del paciente: "+pla.getSurname()+"\n");
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
}
