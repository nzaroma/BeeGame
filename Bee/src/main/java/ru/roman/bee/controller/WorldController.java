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
	public  InputController inputController;
	private UnitInteractions unitInteractions;
	private BulletController bulletController;
	private UnitController unitController; // = new UnitController(unitArray);
	private PlayerController playerController;
	static Map<UserKeys, Boolean> keyMap = new HashMap<UserKeys, Boolean>();
	public  World world;

	static {
		keyMap.put(UserKeys.DOWN, false);
		keyMap.put(UserKeys.LEFT, false);
		keyMap.put(UserKeys.RIGHT, false);
		keyMap.put(UserKeys.UP, false);
	}

	public WorldController(World world) {
		this.world = world;  
		this.player = world.getPlayer();
		this.unitArray = world.getUnitList();
		bulletArray = world.getBulletList();
		playerController = new PlayerController(world);
		unitController = new UnitController(world);
		bulletController = new BulletController(world);
//		inputController = new InputController(player, playerController);
		inputController = new InputController(this);
		unitInteractions = new UnitInteractions(world);
		
	}
	
	public void processInput(float delta) {
		inputController.processInput(delta); 
	}
	
	public void processPlayer(float delta) {
		playerController.processPlayer(delta);
	}
	
	public void processUnits(float delta) {
		unitController.processUnits(delta);
	}
	
	public void processBullets(float delta) {
		bulletController.processBullets(delta);
	}
	
	public void processInteractions(float delta) {
		unitInteractions.processInteractions(delta);
	}
	
	public void update(float delta) {
		inputController.processInput(delta);  // processInput();
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
