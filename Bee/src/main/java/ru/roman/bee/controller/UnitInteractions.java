package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.utils.Array;

public class UnitInteractions {
	
	private Player player;
	private Array<Unit> unitArray;
	private Array<Bullet> bulletArray;
	
	public UnitInteractions(World world) {
		this.player = world.getPlayer();
		this.unitArray = world.getUnitList();
		this.bulletArray = world.getBulletList();
	}
	
	public void processInteractions(float delta) {
		processPlayerIntersectUnit();
		processBulletIntersectUnit();
	}
	
	private void processPlayerIntersectUnit() {
		Iterator<Unit> iterator = unitArray.iterator();
		while(iterator.hasNext()) {
			Unit tempUnit = iterator.next();
			if(player.getPlayerBounds().overlaps(tempUnit.getBounds())) {
				tempUnit.setOverlapped(true);
				player.increaseEnergy(tempUnit.getEnergy());
				tempUnit.setEnergy(0);
//				iterator.remove();			
				//delete units after some time
			}
		}
	}
	
	private void processBulletIntersectUnit() {
		Iterator<Bullet> iterator = bulletArray.iterator();		
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			Iterator<Unit> iteratorUnit = unitArray.iterator();
			while(iteratorUnit.hasNext()) {
				Unit unit = iteratorUnit.next();
				if(bullet.getBounds().overlaps(unit.getBounds())) {
					unit.setOverlapped(true);
				}
			}
		}
	}
	
}
