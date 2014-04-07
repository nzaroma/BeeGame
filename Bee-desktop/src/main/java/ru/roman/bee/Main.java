package ru.roman.bee;

import ru.roman.bee.screens.GameScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Bee";
		cfg.useGL20 = false;
		cfg.width = 1100;
		cfg.height = 600;
		
		new LwjglApplication(new BeeGameReal(), cfg);
	}
}
