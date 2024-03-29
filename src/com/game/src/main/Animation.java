package com.game.src.main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;

	public Animation(int speed, BufferedImage...args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}	
	}
	
	public void nextFrame(){
		for(int i = 0; i < frames; i++) {
			if(count == i)
				currentImg = images[i];
			
		}
		
		count++;
		if(count >= frames)
			count = 0;
	}
	
	public void drawAnimation(Graphics g, int x, int  y){
		g.drawImage(currentImg, x, y, null);
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
		}
	}

