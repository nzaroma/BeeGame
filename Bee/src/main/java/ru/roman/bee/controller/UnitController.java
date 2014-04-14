package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.unit.Flower;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.unit.Wasp;
import ru.roman.bee.model.unit.utils.UnitUtils;
import ru.roman.bee.model.world.World;
import ru.roman.bee.utils.StringUtils;
import ru.roman.bee.view.WorldRenderer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class UnitController {
	private Array<Unit> unitArray; // = new Array<Unit>();
	private Array<Unit> flowerArray;
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
		flower.getPosition().x = MathUtils.random(0, WorldConstants.WORLD_WIDTH
				- Flower.SIZE); // 800 replace with parameter
		flower.getPosition().y = player.getPosition().y
				+ WorldConstants.WORLD_HEIGHT + 200;
		// ///////////////// разобраться с координатами цветка
		flower.getBounds().x = flower.getPosition().x;
		flower.getBounds().y = flower.getPosition().y;
		unitArray.add(flower);
		lastDropTime = System.nanoTime();
	}
	
	private void spawnWasp() {
		Unit wasp = new Wasp();
		wasp.getPosition().x = MathUtils.random(0, WorldConstants.WORLD_WIDTH
				- Flower.SIZE);
		wasp.getPosition().y = player.getPosition().y + + WorldConstants.WORLD_HEIGHT;
		wasp.getBounds().x = wasp.getPosition().x;
		wasp.getBounds().y = wasp.getPosition().y;
		unitArray.add(wasp);
		
	}

	private void processAddingFlowers() {
		Unit lastFlower = UnitUtils.getLastUnitOfType(unitArray, Flower.class);
		if (player.getPosition().y > lastFlower.getPosition().y) {
			System.out.println(lastFlower);
			spawnFlower();
		}			
	}	
	
	private void processAddingWasps() {
		Unit lastWasp = UnitUtils.getLastUnitOfType(unitArray, Wasp.class);
		if(lastWasp == null) {
			spawnWasp();
			lastWasp = UnitUtils.getLastUnitOfType(unitArray, Wasp.class);
		}
		if (player.getPosition().y > lastWasp.getPosition().y) {
			System.out.println(lastWasp);
			spawnWasp();
		}
	}	

	public void processUnits(float delta) {
		processAddingUnits(delta);
		processWasps(delta);
	}

	private void processAddingUnits(float delta) {
		processAddingFlowers();
		processAddingWasps();
	}

	private void processWasps(float delta) {
		Iterator<Unit> iterator = unitArray.iterator();
		while(iterator.hasNext()) {
			Unit unit = iterator.next();
			if(unit instanceof Wasp) {
				if(unit.getPosition().y - player.getPosition().y < 400f) {
					System.out.println(StringUtils.build("unit.getP.y = ", Float.toString(unit.getVelocity().y)));
					System.out.println(StringUtils.build("player.getP.y = ", Float.toString(player.getVelocity().y)));
//					unit.getVelocity().y = 350;		
					unit.getPosition().y = player.getPosition().y + 390;
				}						
			}
			unit.updateVelocity(delta);
		}
	}

}
