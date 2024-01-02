package com.game.src.main;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInp implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
	 	
		Rectangle  quitD = new Rectangle(Game.WIDTH / 2 , 280, 250, 50);
		
		Rectangle retryB = new Rectangle(Game.WIDTH / 2, 180, 250, 50);
		Rectangle  quitM = new Rectangle(Game.WIDTH / 5 + 200, 350, 100, 50);
		 
		if(Game.state == Game.STATE.DEATH) {
			if(quitD.contains(mx, my)) {
				System.exit(1);
			}
		}else if(Game.state == Game.STATE.MENU){
			if(quitM.contains(mx, my)) {
				System.exit(1);
			}
		}
		
		if(Game.state != Game.STATE.DEATH) {
			if(mx >= Game.WIDTH / 5 + 200 && mx <= Game.WIDTH / 5 + 300) {
				if (my >= 150 && my <= 250) {
					Game.state = Game.STATE.GAME;
				}
			}
		}
		
		if(Game.state == Game.STATE.DEATH) {
			if(retryB.contains(mx, my)) {
				System.out.println("restart");
				Game.getGame().init();
				Game.state = Game.STATE.GAME;
			}	
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
