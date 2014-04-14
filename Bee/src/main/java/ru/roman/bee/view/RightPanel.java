package ru.roman.bee.view;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.controller.WorldController;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.world.World;
import ru.roman.bee.utils.StringUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RightPanel {
	public static float CAMERA_WIDTH = Gdx.graphics.getWidth()- WorldConstants.WORLD_WIDTH;
	public static float CAMERA_HEIGHT = WorldConstants.WORLD_HEIGHT;
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer = new  ShapeRenderer();
	
	private SpriteBatch batch = new SpriteBatch();
	private Player player;
	private BitmapFont font = new BitmapFont();

	private World world;

	int rowCount = 1;
	int rowPosition;
	
	
	public RightPanel(World world) {
		this.world = world;
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
		drawRightPanel();
	}
	
	
	
public void drawRightPanel() {		
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		String rpPlayer = "player";
		String playerCoord = player.getPosition().x+","+player.getPosition().y;
		String playerVelocity = player.getVelocity().x+","+player.getVelocity().y;
		
		font.draw(batch, rpPlayer, 0, CAMERA_HEIGHT);
		drawRow(StringUtils.build("coordinates: ", playerCoord));
		drawRow(StringUtils.build("velocity: ", playerVelocity));
		int number = 0; 
		for(Unit unit: world.getUnitList()) { // TODO FIND ERROR
			drawRow(StringUtils.build("unit ", unit.getClass().toString(), "  ", Integer.toString(number), " ", unit.getPosition().toString()));
			number++;
		}
		number = 0; 
		for(Bullet bullet: world.getBulletList()) {
			drawRow(StringUtils.build("bullet ", Integer.toString(number), " ", bullet.getPosition().toString()));
			number++;
		}
		
		rowCount=1;
		batch.end();
	}

	public void drawRow(String textToDraw) {
		int rowInterval = 20;
		rowPosition = rowCount*rowInterval;
		font.draw(batch, textToDraw, 0, WorldConstants.WORLD_HEIGHT-rowPosition);
		rowPosition += rowInterval;
		rowCount++;
	}
	
	
}
