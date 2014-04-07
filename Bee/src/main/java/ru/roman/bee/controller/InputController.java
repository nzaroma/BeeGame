package ru.roman.bee.controller;

import java.util.HashMap;
import java.util.Map;

import ru.roman.bee.model.player.Player;

import com.badlogic.gdx.Input.Keys;

public class InputController {
	private Player player;
	private PlayerController playerController;
	static Map<UserKeys, Boolean> keyMap = new HashMap<UserKeys, Boolean>();
	
	public InputController(Player player, PlayerController controller) {
		this.player = player;
		playerController = controller;
	}
	
	enum UserKeys {
		LEFT, RIGHT, UP, DOWN, SHOOT, DEBUG;
	}
	

	static {
		keyMap.put(UserKeys.DOWN, false);
		keyMap.put(UserKeys.LEFT, false);
		keyMap.put(UserKeys.RIGHT, false);
		keyMap.put(UserKeys.UP, false);
		keyMap.put(UserKeys.SHOOT, false);
	}
	
	public void spacePressed() {
		keyMap.put(UserKeys.SHOOT, true);
	}
	
	public void leftPressed() {
		keyMap.put(UserKeys.LEFT, true);
	}

	public void rigthPressed() {
		keyMap.put(UserKeys.RIGHT, true);
	}

	public void upPressed() {
		keyMap.put(UserKeys.UP, true);
	}

	public void downPressed() {
		keyMap.put(UserKeys.DOWN, true);
	}

	// release key

	public void spaceReleased() {
		keyMap.put(UserKeys.SHOOT, false);
	}
	
	public void leftReleased() {
		keyMap.put(UserKeys.LEFT, false);
	}

	public void rightReleased() {
		keyMap.put(UserKeys.RIGHT, false);
	}

	public void downReleased() {
		keyMap.put(UserKeys.DOWN, false);
	}

	public void upReleased() {
		keyMap.put(UserKeys.UP, false);
	}
	
	
	
	public void resetButtons() {
		rightReleased();
		leftReleased();
		upReleased();
		downReleased();
	}
	
	public void releaseButton(int keycode) {
		if(keycode == Keys.DOWN)
			downReleased();
		if(keycode == Keys.UP)
			upReleased();
		if(keycode == Keys.LEFT)
			leftReleased();
		if(keycode == Keys.RIGHT)
			rightReleased();
		if(keycode == Keys.SPACE)
			spaceReleased();
	}
	
	public void processInput() {
		if(keyMap.get(UserKeys.LEFT))
			player.getVelocity().x = -Player.SPEED;
		if(keyMap.get(UserKeys.RIGHT))
			player.getVelocity().x = Player.SPEED;
		if(keyMap.get(UserKeys.DOWN))
			player.getVelocity().y = -Player.SPEED + 300 ;
		if(keyMap.get(UserKeys.UP))
			player.getVelocity().y = Player.SPEED + 100;
		
		if((keyMap.get(UserKeys.LEFT) && keyMap.get(UserKeys.RIGHT)) || (!keyMap.get(UserKeys.LEFT) && !keyMap.get(UserKeys.RIGHT)))
			player.getVelocity().x = 0;
		if((keyMap.get(UserKeys.UP) && keyMap.get(UserKeys.DOWN)) || (!keyMap.get(UserKeys.UP) && !keyMap.get(UserKeys.DOWN)))
			player.getVelocity().y = 100;
		
		if(keyMap.get(UserKeys.SHOOT))
			playerController.processShooting();
		
		
	}
}
