package ru.roman.bee;


import ru.roman.bee.screens.GameScreen;

import com.badlogic.gdx.Game;

public class BeeGameReal extends Game{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		GameScreen game = new GameScreen();
		setScreen(game);
	}

}
