package ru.roman.bee.view.info;

import ru.roman.bee.model.AbstractParent;

public class InfoDrawer {
	public <T extends AbstractParent> void drawInfo(T t) {
		t.setProjectionMatrix(camera.combined);
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
