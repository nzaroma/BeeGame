package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.State;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.world.World;
import ru.roman.bee.utils.StringUtils;

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
			Unit unit = iterator.next();
			if(player.getPlayerBounds().overlaps(unit.getBounds())) {
				unit.setOverlapped(true);
				player.increaseEnergy(unit.getEnergy());
				unit.setEnergy(0);
//				iterator.remove();			
				//delete units after some time
				unit.setState(State.DEAD);
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
					if(!bullet.getParent().equals(unit)) {
						unit.setOverlapped(true);
						unit.setState(State.DEAD);
					}
					
				}
			}
		}
	}
	
}
