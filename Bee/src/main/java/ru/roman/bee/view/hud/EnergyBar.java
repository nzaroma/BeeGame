package ru.roman.bee.view.hud;

import ru.roman.bee.constants.DisplayConstants;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.world.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class EnergyBar {
	
	public static float CAMERA_WIDTH = DisplayConstants.ENERGY_BAR_WIDTH;
	public static float CAMERA_HEIGHT = DisplayConstants.ENERGY_BAR_HEIGHT;
	
	private BitmapFont font = new BitmapFont();
	private SpriteBatch batch = new SpriteBatch();
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer = new  ShapeRenderer();
	
	Player player;
	public EnergyBar(World world) {
		player = world.getPlayer();
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		setCamera(CAMERA_WIDTH/2f, CAMERA_HEIGHT/2); 
		Texture.setEnforcePotImages(false);
		font.setColor(1, 1, 0, 1);
	}
	
	public void setCamera(float x, float y) {
		this.camera.position.set(x, y, 0);
		this.camera.update();
	}
	
	public void render() {
//		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(1,0,0,0));
		shapeRenderer.rect(10, 10, 25, 210);
		shapeRenderer.end();
//		batch.end();
	}
}
