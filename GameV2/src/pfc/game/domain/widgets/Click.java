package pfc.game.domain.widgets;

/**ARP 01/03/2014: I will use this class to store the click status
 *Best success spree in normal
 *Best success spree in bonus
 *Best fail spree in normal
 *Best fail spree in bonus
 *Total number of success
 *Total number of fails 
 *
 * @author Antonio
 *
 */
public class Click {
	private int successNormalClick;
	private int failNormalClick;
	private int sucAuxNormalClick;
	private int failAuxNormalClick;
	private int successBonusClick;
	private int failBonusClick;
	private int sucAuxBonusClick;
	private int failAuxBonusClick;
	private int totalSuccessClick;
	private int totalFailClick;
	
	public Click() {
		super();
		this.successNormalClick = 0;
		this.failNormalClick = 0;
		this.successBonusClick = 0;
		this.failBonusClick = 0;
		this.totalSuccessClick = 0;
		this.totalFailClick = 0;
		this.sucAuxNormalClick=0;
		this.failAuxNormalClick=0;
		this.sucAuxBonusClick=0;
		this.failAuxBonusClick=0;
	}
	public Click(Click click) {
		super();
		this.successNormalClick = click.getSucAuxNormalClick();
		this.failNormalClick = click.getFailNormalClick();
		this.sucAuxNormalClick = click.getSucAuxNormalClick();
		this.failAuxNormalClick = click.getFailAuxNormalClick();
		this.successBonusClick = click.getSuccessBonusClick();
		this.failBonusClick = click.getFailBonusClick();
		this.totalSuccessClick = click.getTotalSuccessClick();
		this.totalFailClick = click.getTotalFailClick();
		this.sucAuxBonusClick=click.getSucAuxBonusClick();
		this.failAuxBonusClick=click.getFailAuxBonusClick();
	}
	public int getSuccessNormalClick() {
		return successNormalClick;
	}
	public void setSuccessNormalClick(int successNormalClick) {
		this.successNormalClick = successNormalClick;
	}
	public int getFailNormalClick() {
		return failNormalClick;
	}
	public void setFailNormalClick(int failNormalClick) {
		this.failNormalClick = failNormalClick;
	}
	public int getSucAuxNormalClick() {
		return sucAuxNormalClick;
	}
	public void setSucAuxNormalClick(int sucAuxNormalClick) {
		this.sucAuxNormalClick = sucAuxNormalClick;
	}
	public int getFailAuxNormalClick() {
		return failAuxNormalClick;
	}
	public void setFailAuxNormalClick(int failAuxNormalClick) {
		this.failAuxNormalClick = failAuxNormalClick;
	}
	public int getSuccessBonusClick() {
		return successBonusClick;
	}
	public void setSuccessBonusClick(int successBonusClick) {
		this.successBonusClick = successBonusClick;
	}
	public int getFailBonusClick() {
		return failBonusClick;
	}
	public void setFailBonusClick(int failBonusClick) {
		this.failBonusClick = failBonusClick;
	}
	public int getTotalSuccessClick() {
		return totalSuccessClick;
	}
	public void setTotalSuccessClick(int totalSuccessClick) {
		this.totalSuccessClick = totalSuccessClick;
	}
	public int getTotalFailClick() {
		return totalFailClick;
	}
	public void setTotalFailClick(int totalFailClick) {
		this.totalFailClick = totalFailClick;
	}
	public int getSucAuxBonusClick() {
		return sucAuxBonusClick;
	}
	public void setSucAuxBonusClick(int sucAuxBonusClick) {
		this.sucAuxBonusClick = sucAuxBonusClick;
	}
	public int getFailAuxBonusClick() {
		return failAuxBonusClick;
	}
	public void setFailAuxBonusClick(int failAuxBonusClick) {
		this.failAuxBonusClick = failAuxBonusClick;
	}
}