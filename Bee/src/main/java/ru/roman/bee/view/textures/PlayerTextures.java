package ru.roman.bee.view.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface PlayerTextures {
	public Texture beeTexture = new Texture(Gdx.files.internal("player/honeybee_mini1.png"));    //"bee.png"
	public Sprite beeSprite = new Sprite(beeTexture);
	
	
}
