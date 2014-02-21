package pfc.game.domain;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class Game1Desktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Settings settings = new Settings();
		settings.padding=2;
		settings.maxHeight=1024;
		settings.maxWidth=1024;
		settings.incremental=true;
		TexturePacker.process(settings, "images", "data");
		new JoglApplication(new Game1(), "Game 1",320,480,false);

	}

}
