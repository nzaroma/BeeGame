package ru.roman.bee.controller;

import java.util.Iterator;

import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.shots.Sting;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.utils.Array;

public class BulletController {
	
	private Array<Bullet> bulletArray;
	private Player player;
	
	public BulletController(World world) {
		bulletArray = world.getBulletList();
		player = world.getPlayer();
	}
	
	public void processBullets(float delta) {
		Iterator<Bullet> iterator = bulletArray.iterator();
		while(iterator.hasNext()) {
			Bullet bullet = iterator.next();
			if(bullet instanceof Sting) {
				bullet.getVelocity().y = Sting.SPEED;
			}
			else bullet.getVelocity().y = bullet.SPEED;
			bullet.updateVelocity(delta);
			if(bullet.getPosition().y - player.getPosition().y > 1000) { //replace with constant
				iterator.remove();
			}
		}
	}
	
}
