package ru.roman.bee.controller;

import java.util.HashMap;
import java.util.Map;

import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.utils.Array;

public class WorldController {
	enum UserKeys {
		LEFT, RIGHT, UP, DOWN, SHOOT;
	}

	
	public Player player;
	private Array<Unit> unitArray;
	private Array<Bullet> bulletArray;
	public InputController inputController;
	public UnitInteractions unitInteractions;
	private BulletController bulletController;
	private UnitController unitController; // = new UnitController(unitArray);
	private PlayerController playerController;
	static Map<UserKeys, Boolean> keyMap = new HashMap<UserKeys, Boolean>();

	static {
		keyMap.put(UserKeys.DOWN, false);
		keyMap.put(UserKeys.LEFT, false);
		keyMap.put(UserKeys.RIGHT, false);
		keyMap.put(UserKeys.UP, false);
	}

	public WorldController(World world) {
		this.player = world.getPlayer();
		this.unitArray = world.getUnitList();
		bulletArray = world.getBulletList();
		playerController = new PlayerController(world);
		unitController = new UnitController(world);
		bulletController = new BulletController(world);
		inputController = new InputController(player, playerController);
		unitInteractions = new UnitInteractions(world);
		
	}
	
	public void update(float delta) {
		inputController.processInput();  // processInput();
		playerController.processPlayer(delta);		
		unitController.processUnits(delta);
		bulletController.processBullets(delta);
		unitInteractions.processInteractions(delta);
	}

	public void resetButtons(){
		inputController.resetButtons();
	}
	
	public void releaseButton(int keycode) {
		inputController.releaseButton(keycode);
	}
	
}
