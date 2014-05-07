package ru.roman.bee.model.world;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.controller.WorldController;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.Unit;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	public static Array<Unit> unitList = new Array<Unit>();
	public static Array<Bullet> bulletList= new Array<Bullet>();
	private Player player;

	public int width;
	public int height;

	public Array<Unit> getUnitList() {
		return unitList;
	}
	
	public Array<Bullet> getBulletList() {
		return bulletList;
	}

	public Player getPlayer() {
		return player;
	}

	public World() {
		width = WorldConstants.WORLD_WIDTH;
		height = WorldConstants.WORLD_HEIGHT;
		createWorld();
	}

	public void createWorld() {
		player = new Player(new Vector2(600, 2));
//		unitList.add(new Brick(new Vector2(0, 0)));
//		unitList.add(new Brick(new Vector2(1, 0)));
//		unitList.add(new Brick(new Vector2(3, 0)));
//		unitList.add(new Brick(new Vector2(3, 0)));
//		unitList.add(new Brick(new Vector2(4, 0)));
//		unitList.add(new Brick(new Vector2(5, 0)));
//		unitList.add(new Brick(new Vector2(6, 0)));
//		unitList.add(new Brick(new Vector2(7, 0)));
	}
}
