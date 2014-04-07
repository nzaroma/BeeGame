package ru.roman.bee.model.unit;

import com.badlogic.gdx.math.Vector2;

public class Flower extends Unit {
	public static final float SIZE = 64;
	public static final float SPEED = 200f;
	static final int ENERGY = 100; //
	
	public Flower() {
		super();
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.energy = ENERGY;
	}
	
	public Flower(Vector2 pos) {
		super(pos);		
	}
	
//	@Override
//	public int getEnergy() {
//		return energy;
//	}

	
}
