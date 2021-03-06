package ru.roman.bee.view;


import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.model.AbstractParent;
import ru.roman.bee.model.player.Player;
import ru.roman.bee.model.shots.Bullet;
import ru.roman.bee.model.unit.Flower;
import ru.roman.bee.model.unit.Unit;
import ru.roman.bee.model.unit.Wasp;
import ru.roman.bee.model.world.World;
import ru.roman.bee.utils.StringUtils;
import ru.roman.bee.view.textures.PlayerTextures;
import ru.roman.bee.view.textures.UnitTextures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer implements PlayerTextures, UnitTextures{
	public static int CAMERA_WIDTH = 800;  //800
	public static int CAMERA_HEIGHT= 600;
	
	public static final boolean withTextures = true;
	
	int rowCount = 1;
	int rowPosition;
	
	private World world;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer = new  ShapeRenderer();
	
	public SpriteBatch batch = new SpriteBatch();
	Rectangle rectangle;
	Player player;
	BitmapFont font = new BitmapFont();
	
	
	
	public int width;
	public int height;
	public float ppux;
	public float ppuy;
	//
	private float x1, y1; 
	private Color color = new Color(1,0,1,1);
	
	public void setSize(int w, int h) {
		width = w;
		height =h;
		ppux = (float)width/CAMERA_WIDTH;
		ppuy = (float)height/CAMERA_HEIGHT;
	}
	
	public void setCamera(float x, float y) {
		this.camera.position.set(x, y, 0);
		this.camera.update();
	}
	
	public WorldRenderer(World world) {
		this.world = world;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.player = world.getPlayer();
//		setCamera(CAMERA_WIDTH/2f, CAMERA_HEIGHT/2f);
		setCamera(CAMERA_WIDTH/2f, player.getPosition().y);
		Texture.setEnforcePotImages(false);
		font.setColor(0, 1, 0, 1);
		//beesprite
//		beeSprite.rotate(180);
		beeSprite.setSize(100, 100);
	}
	
	public void render(float delta) {
		drawUnits(delta);
		drawPlayer();
		
		drawBullet();
		drawUI();
	}
	
	
	
	public void drawUI() {
		drawInfo();
//		drawRightPanel();
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
		
		font.draw(batch, rpPlayer, 800, 400);
		
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
	
	
	public void drawUnits(float delta) {
		if (withTextures) {
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			for(Unit unit: world.getUnitList()) {
				if(unit.isOverlapped()) continue;
				rectangle = unit.getBounds();
				x1 = unit.getPosition().x; //float
				y1 = unit.getPosition().y;
				if(unit instanceof Flower) {
					batch.draw(flowerTexture, x1, y1);		
				}
				if(unit instanceof Wasp) {
					
					beeSprite.setBounds(x1-50, y1-100, 100, 100);
//					beeSprite.setPosition(x1, y1);
					beeSprite.setRotation(180);
					beeSprite.draw(batch);	
				}
				else {
					
				}
						
			}
			batch.end();
			for(Unit unit: world.getUnitList()) {
				drawUnitInfo(unit);
			}
//		}
//		else {
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.begin(ShapeType.Line);
			for(Unit unit: world.getUnitList()) {
				if(unit.isOverlapped()) continue;
				rectangle = unit.getBounds();
				x1 = unit.getPosition().x; //float
				y1 = unit.getPosition().y;				
				shapeRenderer.setColor(color);
				shapeRenderer.rect(x1, y1, rectangle.width, rectangle.height);				
				drawUnitInfo(unit); 
			}
			shapeRenderer.end();				
		}
	}
	
	public void drawPlayer() {
		setCamera(CAMERA_WIDTH/2f, player.getPosition().y+200);
		if(withTextures) {			
			player = world.getPlayer();
			rectangle = player.getPlayerBounds();
			x1 = player.getPosition().x; // + rectangle.x
			y1 = player.getPosition().y; // + rectangle.y
			batch.setProjectionMatrix(camera.combined);
			batch.begin();		
			float xdraw;
			float ydraw;
			xdraw = x1 - Math.abs((rectangle.width - 100)/2);
			ydraw = y1 - Math.abs((rectangle.height - 100)/2);
			batch.draw(beeTexture, xdraw, ydraw, 100, 100);
			batch.end();
//		}
//		else {
			shapeRenderer.setProjectionMatrix(camera.combined);
			player = world.getPlayer();
			shapeRenderer.begin(ShapeType.Line);
			rectangle = player.getPlayerBounds();
			x1 = player.getPosition().x; // + rectangle.x
			y1 = player.getPosition().y; // + rectangle.y
			shapeRenderer.setColor(new Color(1,0,0,1));
			shapeRenderer.rect(x1, y1, rectangle.width, rectangle.height);
			shapeRenderer.end();
		}
		
	}
	
	public void drawBullet() {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		for(Bullet bullet: world.getBulletList()) {
			rectangle = bullet.getBounds();
			x1 = bullet.getPosition().x;
			y1 = bullet.getPosition().y;
			shapeRenderer.setColor(color);
			shapeRenderer.rect(x1, y1, rectangle.width, rectangle.height);
			drawBulletInfo(bullet);
			
		}
		shapeRenderer.end();
	}
	
	private void drawBulletInfo(Bullet bullet) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		
		String playerCoord = bullet.getPosition().x+","+bullet.getPosition().y;
		String playerVelocity = bullet.getVelocity().x+","+bullet.getVelocity().y;
		String bulletBounds = bullet.getBounds().x +"," +bullet.getBounds().y;
		
		// font will be always displayed
		
			font.draw(batch, playerCoord, bullet.getPosition().x, bullet.getPosition().y+bullet.getBounds().height);
			font.draw(batch, bulletBounds, bullet.getPosition().x , bullet.getPosition().y+bullet.getBounds().height-20);
//			font.draw(batch, bulletBounds, player.getPosition().x - fontWidth, player.getPosition().y+player.getPlayerBounds().height-40);
		
		font.draw(batch, playerVelocity, 10, 580);
		batch.end();
	}	
	
	public void drawUnitInfo(Unit unit) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		String unitCoord = unit.getPosition().x+","+unit.getPosition().y;
		font.draw(batch, unitCoord, unit.getPosition().x, unit.getPosition().y+unit.getBounds().height);
		font.draw(batch, unit.getClass().toString(), unit.getPosition().x , unit.getPosition().y+unit.getBounds().height-20);
//		System.out.println(StringUtils.build("unit.getP.y = ", Float.toString(unit.getPosition().y)));
		batch.end();
	}
	
	public void drawInfo() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		String playerCoord = player.getPosition().x+","+player.getPosition().y;
		String playerVelocity = player.getVelocity().x+","+player.getVelocity().y;		
		// font will be always displayed
		float fontWidth = font.getBounds(playerCoord).width;
		if(fontWidth > CAMERA_WIDTH - player.SIZE - player.getPosition().x) {
			font.draw(batch, playerCoord, player.getPosition().x - fontWidth, player.getPosition().y+player.getPlayerBounds().height);
			font.draw(batch, playerVelocity, player.getPosition().x - fontWidth, player.getPosition().y+player.getPlayerBounds().height-20);
			font.draw(batch, Integer.toString(player.getEnergy()), player.getPosition().x - fontWidth, player.getPosition().y+player.getPlayerBounds().height-40);
		}
		else {
			font.draw(batch, playerCoord, player.getPosition().x+player.getPlayerBounds().width, player.getPosition().y+player.getPlayerBounds().height);
			font.draw(batch, playerVelocity, player.getPosition().x, player.getPosition().y+player.getPlayerBounds().height-20);
			font.draw(batch, Integer.toString(player.getEnergy()), player.getPosition().x+player.getPlayerBounds().width, player.getPosition().y+player.getPlayerBounds().height-40);
		}
		font.draw(batch, playerVelocity, 10, 580);
		batch.end();
	}
	
	
	
}























