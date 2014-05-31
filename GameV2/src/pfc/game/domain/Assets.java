package pfc.game.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

//ARP-09/10/13: The focus of this class is to upload all resources needed on memory
public class Assets {
	
	/** This variable contains the textures package. */
	private static TextureAtlas atlas;
	
	/** These are regions inside of the textures package image.*/
	private static AtlasRegion mainBackground;
	private static AtlasRegion gameBackground;
	private static AtlasRegion rec1;
	private static AtlasRegion rec2;
	private static AtlasRegion easyOn;
	private static AtlasRegion easyOff;
	private static AtlasRegion mediumOn;
	private static AtlasRegion mediumOff;
	private static AtlasRegion hardOn;
	private static AtlasRegion hardOff;
	private static AtlasRegion start;
	private static AtlasRegion title;
	private static AtlasRegion corMsg;
	private static AtlasRegion uncMsg;
	private static AtlasRegion bcBut;
	private static AtlasRegion gameBonusBackground;
	private static AtlasRegion lifes;
	private static AtlasRegion bonus;
	private static Music music;
	
	
	public static TextureAtlas getAtlas() {
		return atlas;
	}

	public static void setAtlas(String path) {
		atlas = new TextureAtlas(Gdx.files.internal("data/pack"));
		Assets.load();
	}
	public static AtlasRegion getRec1() {
		return rec1;
	}

	public static void setRec1(AtlasRegion rec1) {
		Assets.rec1 = rec1;
	}

	public static AtlasRegion getRec2() {
		return rec2;
	}
	public static void setBackGroundButton(AtlasRegion bcBut) {
		Assets.bcBut = bcBut;
	}

	public static AtlasRegion getBackGroundButton() {
		return bcBut;
	}


	public static void setRec2(AtlasRegion rec2) {
		Assets.rec2 = rec2;
	}
	public static AtlasRegion getStart() {
		return start;
	}

	public static void setStart(AtlasRegion start) {
		Assets.start = start;
	}

	public static AtlasRegion getTitle() {
		return title;
	}

	public static void setTitle(AtlasRegion title) {
		Assets.title = title;
	}

	public static Music getMusic() {
		return music;
	}

	public static void setMusic(String path) {
		music = Gdx.audio.newMusic(Gdx.files.internal(path));
	}
	public static AtlasRegion getGameBackground() {
		return gameBackground;
	}

	public static void setGameBackground(AtlasRegion gamebackground) {
		Assets.gameBackground = gamebackground;
	}
	public static AtlasRegion getCorMsg() {
		return corMsg;
	}

	public static void setCorMsg(AtlasRegion corMsg) {
		Assets.corMsg = corMsg;
	}
	public static AtlasRegion getMainBackground() {
		return mainBackground;
	}

	public static void setMainBackground(AtlasRegion mainBackground) {
		Assets.mainBackground = mainBackground;
	}

	public static AtlasRegion getUncMsg() {
		return uncMsg;
	}

	public static void setUncMsg(AtlasRegion uncMsg) {
		Assets.uncMsg = uncMsg;
	}
	public static AtlasRegion getEasyOn() {
		return easyOn;
	}

	public static void setEasyOn(AtlasRegion easyOn) {
		Assets.easyOn = easyOn;
	}

	public static AtlasRegion getEasyOff() {
		return easyOff;
	}

	public static void setEasyOff(AtlasRegion easyOff) {
		Assets.easyOff = easyOff;
	}

	public static AtlasRegion getMediumOn() {
		return mediumOn;
	}

	public static void setMediumOn(AtlasRegion mediumOn) {
		Assets.mediumOn = mediumOn;
	}

	public static AtlasRegion getMediumOff() {
		return mediumOff;
	}

	public static void setMediumOff(AtlasRegion mediumOff) {
		Assets.mediumOff = mediumOff;
	}

	public static AtlasRegion getHardOn() {
		return hardOn;
	}

	public static void setHardOn(AtlasRegion hardOn) {
		Assets.hardOn = hardOn;
	}

	public static AtlasRegion getHardOff() {
		return hardOff;
	}

	public static void setHardOff(AtlasRegion hardOff) {
		Assets.hardOff = hardOff;
	}

	public static AtlasRegion getGameBonusBackground() {
		return gameBonusBackground;
	}

	public static void setGameBonusBackground(AtlasRegion gameBonusBackground) {
		Assets.gameBonusBackground = gameBonusBackground;
	}

	public static AtlasRegion getLifes() {
		return lifes;
	}

	public static void setLifes(AtlasRegion lifes) {
		Assets.lifes = lifes;
	}

	public static AtlasRegion getBonus() {
		return bonus;
	}

	public static void setBonus(AtlasRegion bonus) {
		Assets.bonus = bonus;
	}

	/**
	 * Load.
	 */
	public static void load(){
		
		mainBackground = atlas.findRegion("mainBackground");
		gameBackground = atlas.findRegion("gameBackground");
		rec1 = atlas.findRegion("rec1");
		rec2 = atlas.findRegion("rec2");
		easyOn = atlas.findRegion("easyOn");
		easyOff= atlas.findRegion("easyOff");
		mediumOn = atlas.findRegion("mediumOn");
		mediumOff = atlas.findRegion("mediumOff");
		hardOn = atlas.findRegion("hardOn");
		hardOff= atlas.findRegion("hardOff");
		start = atlas.findRegion("start");
		title = atlas.findRegion("title");
		corMsg=atlas.findRegion("corMsg");
		uncMsg=atlas.findRegion("uncMsg");
		bcBut=atlas.findRegion("backgroundButton");
		gameBonusBackground=atlas.findRegion("gameBonusBackground");
		lifes=atlas.findRegion("lifes");
		bonus=atlas.findRegion("bonus");
		Assets.setMusic("data/music.mp3");
		
		
	}
	
	public static void dispose(){
		atlas.dispose();
	}
	
	
	
	
}
