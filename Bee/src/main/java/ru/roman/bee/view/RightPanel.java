package ru.roman.bee.view;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.controller.WorldController;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.world.World;

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
		setCamera(CAMERA_WIDTH/2f, player.getPosition().y);
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
		
		int rowInterval = 20;
		int rowPosition = 20;
		int playerBlock = 0;
		int playerRowsCount;
//		int unitBlock = playerBlock + playerRowsCount*rowInterval;
		int bulletBlock;
		int rowCount = 1;
		
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		String rpPlayer = "player";
		String playerCoord = player.getPosition().x+","+player.getPosition().y;
		String playerVelocity = player.getVelocity().x+","+player.getVelocity().y;
		
		font.draw(batch, rpPlayer, 100, CAMERA_HEIGHT);
		
		drawRow(rpPlayer);
		drawRow(rpPlayer);
		drawRow(rpPlayer);

		
		batch.end();
	}

	public void drawRow(String textToDraw) {
		int rowInterval = 20;
		rowPosition = rowCount*rowInterval;
		font.draw(batch, textToDraw, WorldConstants.WORLD_WIDTH, WorldConstants.WORLD_HEIGHT+rowPosition+rowInterval);
		rowPosition += rowInterval;
		rowCount++;
	}
	
	
}
