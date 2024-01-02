package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB{
	
	Random r = new Random();
	private Textures tex;
	private Game game;
	private Controller c;
	Animation anim;
	private int speed = r.nextInt(6) + 1;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
	
		super(x,y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animation(7, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
		}
	
	public void tick() {
		
		x -= speed;
		
		if (x < -50) {
			Game.GalaxyHealth -= 50;
			speed = r.nextInt(2) + 1;
			x = (Game.WIDTH * Game.SCALE);
			
			y = r.nextInt((Game.HEIGHT * Game.SCALE ) -75 ) + 52;
			
			if (Game.GalaxyHealth <= 0) {
				Game.state = STATE.DEATH;
				
			}
			
			
		}
		for(int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				
				anim = new Animation(10, tex.death[0]);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				game.setTot_Enemy_Killed(game.getTot_Enemy_Killed() + 1);
				
				
				
			}
		}
		
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		anim.drawAnimation(g, (int)x, (int)y);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64 );
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
}
