package ru.roman.bee.screens;

import ru.roman.bee.constants.WorldConstants;
import ru.roman.bee.controller.WorldController;
import ru.roman.bee.model.world.World;
import ru.roman.bee.view.RightPanel;
import ru.roman.bee.view.WorldRenderer;
import ru.roman.bee.view.hud.EnergyBar;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen, InputProcessor{
	
	private World world;
	private WorldRenderer renderer;
	private RightPanel rightPanel;
	private EnergyBar energyBar;
	
	private WorldController worldController;

	private int width, height;
	
	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		rightPanel = new RightPanel(world);
		energyBar = new EnergyBar(world);
		
		worldController = new WorldController(world);
		Gdx.input.setInputProcessor(this);
		
		
	}
	

	@Override
	public boolean keyDown(int keycode) {
		changeNavigation(keycode);
		shotProcessing(keycode);
		debug(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {	
		worldController.inputController.releaseButton(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!Gdx.app.getType().equals(ApplicationType.Android)) return false;
		changeNavigation(screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(!Gdx.app.getType().equals(ApplicationType.Android)) return false;
		worldController.inputController.resetButtons();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		changeNavigation(screenX, screenY);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		worldController.update(delta);
		
		Gdx.gl.glViewport(0,0,WorldConstants.WORLD_WIDTH,WorldConstants.WORLD_HEIGHT);
		renderer.render();		
		
		Gdx.gl.glViewport(WorldConstants.WORLD_WIDTH,0,300,WorldConstants.WORLD_HEIGHT);   
		rightPanel.render();
		
		Gdx.gl.glViewport(0, 200, 50, 200);
		energyBar.render();
		
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height; // ??? a ne naoborot?
		
	}

	private void changeNavigation(int x, int y) {
		worldController.resetButtons();
		if(height - y > worldController.player.getPosition().y * renderer.ppuy)
			worldController.inputController.upPressed();
		if(height - y < worldController.player.getPosition().y * renderer.ppuy)
			worldController.inputController.downPressed();
		if(x > worldController.player.getPosition().x * renderer.ppux)
			worldController.inputController.rigthPressed();
		if(x < worldController.player.getPosition().x * renderer.ppux)
			worldController.inputController.leftPressed();
	}
	
	private void changeNavigation(int keycode) {
		if(keycode == Keys.DOWN)
			worldController.inputController.downPressed();
		if(keycode == Keys.UP)
			worldController.inputController.upPressed();
		if(keycode == Keys.LEFT)
			worldController.inputController.leftPressed();
		if(keycode == Keys.RIGHT)
			worldController.inputController.rigthPressed();
	}
	
	private void shotProcessing(int keycode) {
		if(keycode == Keys.SPACE)
			worldController.inputController.spacePressed();
	}
	
	private void debug(int keycode) {
		if(keycode== Keys.F1) {
			int a = 0;
			a =2 ;
		}
	}
	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		renderer.beeTexture.dispose();
		renderer.batch.dispose();
		
	}

}
