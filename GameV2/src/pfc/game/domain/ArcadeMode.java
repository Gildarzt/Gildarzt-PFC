package pfc.game.domain;

public class ArcadeMode {
	private int difficult;
	private int tries;
	private boolean speedInc;
	private boolean bonus;
	private boolean sound;
	
	public ArcadeMode(int difficult, int tries, boolean speedInc, boolean bonus,boolean sound) {
		super();
		this.difficult = difficult;
		this.tries = tries;
		this.speedInc = speedInc;
		this.bonus = bonus;
		this.sound=sound;
	}

	public int getDifficult() {
		return difficult;
	}

	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public boolean isSpeedInc() {
		return speedInc;
	}

	public void setSpeedInc(boolean speedInc) {
		this.speedInc = speedInc;
	}

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}
	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
