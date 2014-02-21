package pfc.game.persistence;

/** ARP-18/11/13: this class is used to store the result of a test, every time that a patient
 * click on the screen, it generates a result that stores in the patient's results array.
 * @author Antonio
 *
 */
public class Result{
	
	private int IdTest;
	private double Difficult;
	private boolean res;
	private String descrp;
	
	public Result(int IdTest, double Difficult, boolean res,String descrp){
		this.IdTest=IdTest;
		this.Difficult=Difficult;
		this.res=res;
		this.descrp=descrp;
		
	}

	public int getIdTest() {
		return IdTest;
	}

	public void setIdTest(int IdTest) {
		this.IdTest = IdTest;
	}

	public String getDescrp() {
		return descrp;
	}

	public void setDescrp(String descrp) {
		this.descrp = descrp;
	}

	public double getDifficult() {
		return Difficult;
	}

	public void setDifficult(double Difficult) {
		this.Difficult = Difficult;
	}

	public boolean isRes() {
		return res;
	}

	public void setRes(boolean res) {
		this.res = res;
	}
}
