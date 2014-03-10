package pfc.game.comunication;

/**ARP-05/03/2014: This class has all the constant variables that I need to parse the Json from the 
 * client
 * @author Antonio
 *
 */
public class Code {
	private Code() {
	}
	public static int ReturnCode(String code){
		int res=0;
		if(code.startsWith("Code"))
			res=1;
		else if(code.startsWith("Name"))
			res=2;
		else if(code.startsWith("Surname"))
			res=3;
		else if(code.startsWith("IdTest"))
			res=4;
		else if(code.startsWith("InitialDifficult"))
			res=5;
		else if(code.startsWith("nSuccess"))
			res=6;
		else if(code.startsWith("nFails"))
			res=7;
		else if(code.startsWith("SuccessSpree"))
			res=8;
		else if(code.startsWith("FailSpree"))
			res=9;
		else if(code.startsWith("SuccessBonusSpree"))
			res=10;
		else if(code.startsWith("FailBonusSpree"))
			res=11;
		if(code.startsWith("Mode"))
			res=12;
		else if(code.startsWith("Result"))
			res=13;
		if(code.startsWith("Difficult"))
			res=14;
		
		return res;
	}
}
