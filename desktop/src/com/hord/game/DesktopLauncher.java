package com.hord.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hord.game.main.MainGame;

import static com.hord.game.support.Constants.GAME_HEIGHT;
import static com.hord.game.support.Constants.GAME_WIGHT;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(GAME_WIGHT,GAME_HEIGHT);
		config.setTitle("Platformer");
		new Lwjgl3Application(new MainGame(), config);
	}
}
