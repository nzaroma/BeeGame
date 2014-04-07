package ru.roman.bee.controller;

import ru.roman.bee.constants.PlayerRelatedConstants;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.shots.Sting;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.utils.Array;

public class PlayerController {
	
	private Player player;
	private float lastY;
	private long lastTimeShoot = System.currentTimeMillis();
	private Array<Bullet> bulletArray; 
	
	public PlayerController(World world) {
		this.player = world.getPlayer();
		lastY = player.getPosition().y;
		bulletArray = world.getBulletList();
	}
	
	public void processPlayer(float delta) {
		player.updateVelocity(delta);
		processEnergyFly();
	}
	
	public void processEnergyFly() {
		if(player.getPosition().y - lastY > PlayerRelatedConstants.ENERGY_DECREASE_DISTANT) {
			player.decreaseEnergy(PlayerRelatedConstants.ENERGY_DECREASE_FLY);
			lastY = player.getPosition().y;
		}
	}
	
	
	public void processShooting() {
		if(System.currentTimeMillis() - lastTimeShoot > PlayerRelatedConstants.DELAY_BETWEEN_SHOTS) {
			lastTimeShoot = System.currentTimeMillis();
			shoot();
		}		
	}
	
	public void shoot() {
		Bullet bullet = new Sting(player.getPosition());		
		player.decreaseEnergy(bullet.getEnergy());
		bullet.getVelocity().y = player.getVelocity().y + 100;
		bulletArray.add(bullet);
	}
	
	
}
