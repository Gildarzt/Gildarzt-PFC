package pfc.game.domain;

public class Try {
	private int id;
	private boolean result;
	private boolean bonus_ready;/**This one is used to know if the try had the bonus option open*/
	private boolean bonus_on;/**This one is used to know if we are on bonus time or not*/
	private int idReport;
	public Try(int id, boolean result, boolean bonus_ready, boolean bonus_on, int idReport) {
		super();
		this.id = id;
		this.result = result;
		this.bonus_ready = bonus_ready;
		this.bonus_on = bonus_on;
		this.idReport=idReport;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isBonus_ready() {
		return bonus_ready;
	}
	public void setBonus_ready(boolean bonus_ready) {
		this.bonus_ready = bonus_ready;
	}
	public boolean isBonus_on() {
		return bonus_on;
	}
	public void setBonus_on(boolean bonus_on) {
		this.bonus_on = bonus_on;
	}
	public int getIdReport() {
		return idReport;
	}
	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}
}
