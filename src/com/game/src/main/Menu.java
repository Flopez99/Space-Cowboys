package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public Rectangle  playB = new Rectangle(Game.WIDTH / 5 + 200, 150, 100, 50);
	public Rectangle  helpB = new Rectangle(Game.WIDTH / 5 + 200, 250, 100, 50);
	public Rectangle  quitB = new Rectangle(Game.WIDTH / 5 + 200, 350, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("TimesRoman", Font.BOLD, 60);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SPACE COWBOYS", Game.WIDTH / 5, 100);
		
		Font fnt1 = new Font("TimesRoman", Font.BOLD, 40);
		g.setFont(fnt1);
		g.drawString("Play", playB.x + 13, playB.y + 35);
		g.drawString("Help", helpB.x + 13, helpB.y + 35);
		g.drawString("Quit", quitB.x + 13, quitB.y + 35);
		g2d.draw(playB);
		g2d.draw(helpB);
		g2d.draw(quitB);

		
		
	}
}
