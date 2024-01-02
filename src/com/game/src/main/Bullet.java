package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{

	private Textures tex;
	private Game game;
	private Controller c;
	public Bullet(double x, double y, Textures tex,Controller c,  Game game) {
		
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.c = c;
	}
	public void tick() {
			x += 5;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32 );
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.bullet[0], (int)x, (int)y, null);
		
	}
	public double getY(){
		return y;
	}
	public double getX() {
		return x;
	}
}


