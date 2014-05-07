package ru.roman.bee.model.shots;

import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.player.Player;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Sting extends Bullet {
	public static final int SPEED = 400;
	static final int SIZE = 10;
	static final int ENERGY_CONSUME = 13;
//	private int direction;
	private Vector2 direction;

	public Sting(Vector2 position) {
		super(position);
	}
	
	
	/**
	 * @param parent
	 * @param target
	 */
	public Sting(AbstractParent parent, AbstractParent target) {
		super(parent, target);
		
	}
	
	public int getEnergy() {
		return ENERGY_CONSUME;
	}

	@Override
	public Rectangle getBounds() {
		return bulletBounds;
	}
	
	public void updateVelocity(float delta) {
		position.add(velocity.x * delta, velocity.y * delta);
//		position.add(velocity.scl(delta));
		bulletBounds.x = position.x;
		bulletBounds.y = position.y;
	}
	@Override
	public Vector2 getDirection() {
		// TODO Auto-generated method stub
		return new Vector2(2000,34);
	}

	public void shootToTarget(float targetX, float targetY) {
		 velocity.set(targetX - position.x, targetY - position.y).nor().scl(Math.min(position.dst(targetX, targetY), SPEED));
	}

}
