package ru.roman.bee.model.unit;

import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.shots.Sting;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wasp extends Unit {
	static final float SIZE = 40;
	Rectangle bounds = new Rectangle();
	// private int energy;
	// private Vector2 position = new Vector2();
	// private Vector2 velocity = new Vector2();
	// private boolean overlapped = false;
	// private Rectangle bounds = new Rectangle();

	public Wasp() {
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
	}

	public Wasp(Vector2 pos) {
		super();
		this.position = pos;

	}

	public void act(Player player) {
		// move
		// shoot
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void updateVelocity(float delta, Player player) {
		position.add(velocity.scl(delta));
		if (position.y - player.getPosition().y < 400) {
			position.y = player.getPosition().y + 390;
		}
		bounds.x = position.x;
		bounds.y = position.y;
	}

	public Vector2 getVelocity() {
		return velocity;
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

	public void setEnergy(int set) {
		energy = set;
	}

	public void shoot() {
		shoot(null); // TODO 
	}

	public void shoot(AbstractParent target) {
		Bullet bullet = new Sting(this, target);
		if (target instanceof Player) {
			Player player = (Player) target;
			bullet.getVelocity().set((player.getPosition().x - position.x), 
					(player.getPosition().y - position.y + player.SPEED/2)).nor()
			.scl(Math.min(position.dst((player.getPosition().x), (player.getPosition().y+player.SPEED/2)), 400));
		}

		// this.decreaseEnergy(bullet.getEnergy());

		World.bulletList.add(bullet);
	}
}
