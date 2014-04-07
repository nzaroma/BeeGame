package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.unit.Flower;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.world.World;
import ru.roman.bee.view.WorldRenderer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class UnitController {
	private Array<Unit> unitArray; // = new Array<Unit>();
	private long lastDropTime; // = TimeUtils.nanoTime();
	private World world;
	private Player player;

	public UnitController(World world) {
		this.unitArray = world.getUnitList();
		this.world = world;
		player = world.getPlayer();
		spawnFlower();
	}
	
	private void spawnFlower() {
		Unit flower = new Flower();
		Rectangle rectangle = flower.getBounds();
		flower.getPosition().x = MathUtils.random(0, WorldConstants.WORLD_WIDTH - Flower.SIZE); // 800 replace with parameter
		flower.getPosition().y = player.getPosition().y + WorldConstants.WORLD_HEIGHT +200;
		/////////////////// разобраться с координатами цветка
		flower.getBounds().x = flower.getPosition().x;
		flower.getBounds().y = flower.getPosition().y;
		unitArray.add(flower);
		lastDropTime = System.nanoTime();
	}
	
	private void processAddingFlowers() {
//		if((System.nanoTime() - lastDropTime) > 1000000000) {
//			spawnFlower();
//		}
		if (player.getPosition().y > unitArray.peek().getPosition().y) {
			spawnFlower();
		}
	}
	
	private void removeFlower(Unit flower) {
		Iterator<Unit> iterator = unitArray.iterator();
		while(iterator.hasNext()) {
			Unit tempFlower = iterator.next();
			if(tempFlower.equals(flower)) {
//				iterator.remove();
			}
		}
	}
	
	private void processMovingAndDeletingFlower(float delta) {
		Iterator<Unit> iterator = unitArray.iterator();
		while(iterator.hasNext()) {
			Unit tempFlower = iterator.next();
			if(tempFlower.getPosition().y < -200) {
				iterator.remove();
			}
			tempFlower.getVelocity().y = -Flower.SPEED;
			tempFlower.updateVelocity(delta);
		}
	}
	public void processUnits(float delta) {
		processFlowers(delta);
	}
	
	private void processFlowers(float delta) {
		processAddingFlowers();
//		processMovingAndDeletingFlower(delta);
	}

}
