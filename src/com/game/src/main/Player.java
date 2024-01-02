package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Player extends GameObject implements EntityA {

		
		
		private double velX = 0;
		private double velY = 0;
		private Textures tex;
		
		Animation anim;
		Game game;
		Controller c;
		
		public Player(double x, double y, Textures tex, Game game, Controller c) {
		
			super(x,y);
			this.tex = tex;
			this.game = game;
			this.c = c;
			anim = new Animation(7, tex.player[0], tex.player[1], tex.player[2]);
			
		}
		
		public void tick() {
			x = x + velX;
			y = y + velY;
			
			if(x <= 0) 
				x = 0;
			if(x >= 640 - 50)
				x = 640 - 50;
			if(y <= 0 )
				y = 0;
			if(y >= 480 - 60)
				y = 480 - 60;
			
			for(int i = 0; i < game.eb.size(); i++) {
				EntityB tempEnt = game.eb.get(i);
				
				if (Physics.Collision(this, tempEnt)) {
					c.removeEntity(tempEnt);
					Game.Health -= 20;
					game.setEnemy_killed(game.getEnemy_killed() + 1);
					
					if(Game.Health <= 0) 
						Game.state = Game.STATE.DEATH;
						
				}
			}
			
			anim.runAnimation();
		}
		
		public Rectangle getBounds() {
			return new Rectangle((int)x, (int)y, 32, 32 );
			
		}
		public void render(Graphics g) {
			anim.drawAnimation(g, (int)x, (int)y);
			
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
		public void setVelX(double velX) {
			this.velX= velX;
		}
		public void setVelY(double velY) {
			this.velY = velY;
		}
	}
