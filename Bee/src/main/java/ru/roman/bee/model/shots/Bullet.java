package ru.roman.bee.model.shots;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Bullet{
	
	public static final int SPEED = 10;
	static final int SIZE = 10;
	static final int ENERGY_CONSUME = 10;
	
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	
	Rectangle bulletBounds = new Rectangle();
	
	private Bullet() {
		bulletBounds.width = SIZE;
		bulletBounds.height = SIZE;
	}
	
	public Bullet(Vector2 position) {
		this();
		this.position.x = position.x;
		this.position.y = position.y;
		bulletBounds.x = position.x;
		bulletBounds.y = position.y;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void updateVelocity(float delta) {
		position.add(velocity.scl(delta));
		bulletBounds.x = position.x;
		bulletBounds.y = position.y;
	}
	
	abstract public int getEnergy(); 
//		return ENERGY_CONSUME;
	abstract public Rectangle getBounds();
	
}
