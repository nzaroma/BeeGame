package ru.roman.bee.model.unit;

import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.utils.StringUtils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Unit extends AbstractParent {
	static final float SIZE = 40;
	
	int energy;
	State state = State.NONE;
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	
	boolean overlapped = false;
	
	Rectangle bounds = new Rectangle();

	public Unit() {
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		state = State.LIVE;
	}

	public Unit(Vector2 pos) {
		super();
		this.position = pos;

	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public void act() {
		
	}
	
	public void updateVelocity(float delta) {
		position.add(velocity.scl(delta));
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public void updateVelocity(float delta, Player player) {
		position.add(velocity.scl(delta));
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isOverlapped() {
		return overlapped;
	}
	
	public void setOverlapped(boolean overlapped) {
		this.overlapped = overlapped; 
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int set){
		energy = set;
	}
	
	public String toString() {
		return StringUtils.build("Unit ", this.getClass().getCanonicalName().toString(), " ", position.toString());
	}
	
	public void shoot() {};
}
