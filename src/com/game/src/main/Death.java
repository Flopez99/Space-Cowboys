package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Death {
	
	public Rectangle  retryB = new Rectangle(Game.WIDTH / 2, 180, 250, 50);
	public Rectangle  quitB = new Rectangle(Game.WIDTH / 2 , 280, 250, 50);
	public Rectangle  score = new Rectangle(Game.WIDTH / 2 , 380, 305, 50);
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("TimesRoman", Font.BOLD, 60);
		g.setFont(fnt0);
		g.setColor(Color.black);
		
		g.fillRect(Game.WIDTH / 2 , 180, 250, 50);
		g.fillRect(Game.WIDTH / 2 , 280, 250, 50);
		g.fillRect(Game.WIDTH / 2 , 380, 305, 50);
		
		if (Game.Health == 0) 
		g.drawString("YOU DIED", Game.WIDTH / 2, 50);
		else {
			g.drawString("YOUR GALAXY", Game.WIDTH / 3, 50);
			g.drawString("WAS CONQUERED", Game.WIDTH / 5, 120);
		}
		
		Font fnt1 = new Font("TimesRoman", Font.BOLD, 40);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("TRY AGAIN", retryB.x + 13, retryB.y + 35);
		g.drawString("QUIT", quitB.x + 13, quitB.y + 35);
		g.drawString("Your Score = " + Game.getGame().tot_enemy_killed, score.x + 13, score.y + 35);
		g2d.draw(retryB);
		g2d.draw(quitB);
		g2d.draw(score);
		}
}
