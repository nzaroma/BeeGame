package ru.roman.bee.model.player;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.controller.BulletController;
import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.IShooting;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.shots.Sting;
import ru.roman.bee.model.world.World;
import ru.roman.bee.utils.StringUtils;
import ru.roman.bee.view.WorldRenderer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends AbstractParent implements IShooting {
	public enum State {
		NONE, MOVING, DEAD;
	}

	public static final float SPEED = 250f;
	public static final float SIZE = 64;

	private int energy = 66;

	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2(); // speed ??

	Rectangle playerBounds = new Rectangle();

	State state = State.NONE;

	public Player(Vector2 positioin) {
		this.position = positioin;
		this.playerBounds.height = SIZE;
		this.playerBounds.width = SIZE;
	}

	public Rectangle getPlayerBounds() {
		return playerBounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void updateVelocity(float delta) {
		position.add(velocity.scl(delta));
		if (position.x < 0)
			position.x = 0;
		if (position.x > WorldConstants.WORLD_WIDTH - getPlayerBounds().width)
			position.x = WorldConstants.WORLD_WIDTH - getPlayerBounds().width;
		if (position.y < 0)
			position.y = 0;
		playerBounds.x = position.x;
		playerBounds.y = position.y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void increaseEnergy(int energy) {
		this.energy += energy;
	}

	public void decreaseEnergy(int energy) {
		this.energy -= energy;
	}
	
	public String toString() {
		return StringUtils.build("player ", getPosition().toString());
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		Bullet bullet = new Sting(this, null);		
		this.decreaseEnergy(bullet.getEnergy());
//		bullet.getVelocity().y = this.getVelocity().y + 100;
		bullet.getVelocity().y = Sting.SPEED;
		World.bulletList.add(bullet);
		
	}
}
