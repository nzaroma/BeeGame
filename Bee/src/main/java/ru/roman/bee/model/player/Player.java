package ru.roman.bee.model.player;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.view.WorldRenderer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	public enum State {
		NONE, MOVING, DEAD;
	}
	
	public static final float SPEED = 250f;
	public static final float SIZE  = 64;
	
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
		if(position.x < 0) position.x = 0;
		if(position.x > WorldConstants.WORLD_WIDTH - getPlayerBounds().width) position.x = WorldConstants.WORLD_WIDTH - getPlayerBounds().width;
		if(position.y < 0) position.y = 0;
		playerBounds.x = position.x;
		playerBounds.y = position.y;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int energy){
		this.energy = energy;
	}
	
	public void increaseEnergy(int energy) {
		this.energy += energy;
	}
	
	public void decreaseEnergy(int energy) {
		this.energy -= energy;
	}
}
 