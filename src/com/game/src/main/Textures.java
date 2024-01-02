package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage [] player = new BufferedImage[3];
	public BufferedImage [] enemy = new BufferedImage[3];
	public BufferedImage [] bullet = new BufferedImage[3];
	public BufferedImage []  death = new BufferedImage[1];
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		 ss = new SpriteSheet(game.getSpriteSheet());
		
		 getTextures();
	}
	private void getTextures() {
		player[0] = ss.grabImage(1, 1, 64, 64);
		player[1] = ss.grabImage(1, 2, 64, 64);
		player[2] = ss.grabImage(1, 3, 64, 64);
		
		bullet [0]= ss.grabImage(2, 1, 64, 64);
		bullet [1]= ss.grabImage(2, 2, 64, 64);
		bullet [2] = ss.grabImage(2, 3, 64, 64);
		 
		enemy[0] = ss.grabImage(3, 1, 64, 64);
		enemy[1] = ss.grabImage(3, 2, 64, 64);
		enemy[2] = ss.grabImage(3, 3, 64, 64);
		
		death[0] = ss.grabImage(1, 1, 64, 64);
	}
}
