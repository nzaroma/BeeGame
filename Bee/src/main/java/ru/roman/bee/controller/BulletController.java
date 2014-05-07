package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.constants.PlayerRelatedConstants;
import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.shots.Sting;
import ru.roman.bee.model.unit.State;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.unit.Wasp;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.utils.Array;

public class BulletController {

	private Array<Bullet> bulletArray;
	private Player player;
	private long lastTimePlayerShoot = System.currentTimeMillis();
	private long lastTimeWaspShoot = System.currentTimeMillis();


	public BulletController(World world) {
		bulletArray = world.getBulletList();
		player = world.getPlayer();
	}

	public void processBullets(float delta) {
		processUnitShooting();  
		Iterator<Bullet> iterator = bulletArray.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();
			bullet.updateVelocity(delta);
			if (Math.abs(bullet.getPosition().y - player.getPosition().y) > PlayerRelatedConstants.LENGTH_OF_DELETE) { 
				// TODO replace with constant
				iterator.remove();
			}			
		}
	}
	
	public void processPlayerShooting() {
		if(System.currentTimeMillis() - lastTimePlayerShoot > PlayerRelatedConstants.DELAY_BETWEEN_SHOTS) {
			lastTimePlayerShoot = System.currentTimeMillis();			
			player.shoot();
		}		
	}
	
	public void processUnitShooting() {
		if(System.currentTimeMillis() - lastTimeWaspShoot > 1500) {
			for(AbstractParent unit: World.unitList) {
				if(unit instanceof Wasp) {
					if(((Wasp) unit).getState() != State.DEAD) {
						((Wasp) unit).shoot(player);
						lastTimeWaspShoot = System.currentTimeMillis();
					}
					
				}
			}
		}
	}

}
