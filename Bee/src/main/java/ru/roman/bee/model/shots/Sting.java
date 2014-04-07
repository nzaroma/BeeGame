package ru.roman.bee.model.shots;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Sting extends Bullet {
	public static final int SPEED = 400;
	static final int SIZE = 10;
	static final int ENERGY_CONSUME = 13;

	public Sting(Vector2 position) {
		super(position);
	}
	
	public int getEnergy() {
		return ENERGY_CONSUME;
	}

	@Override
	public Rectangle getBounds() {
		return bulletBounds;
	}
	
	public void updateVelocity(float delta) {
		position.add(velocity.scl(delta));
		bulletBounds.x = position.x;
		bulletBounds.y = position.y;
	}

}
