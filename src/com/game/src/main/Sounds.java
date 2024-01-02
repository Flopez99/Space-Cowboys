package com.game.src.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	
	void playSound(String filepath) {
		
		try {
			 File blaster = new File(filepath);
			 File music = new File(filepath);
			 
			 if(music.exists()) {
				 
				 AudioInputStream audioInput = AudioSystem.getAudioInputStream(blaster);
				 Clip clip = AudioSystem.getClip();
				 clip.open(audioInput);
				 clip.start();
				 
				 
			 
			 }else {
				System.out.println("Inexistent File"); 
			 }
			 
			 if(blaster.exists()) {
				 
				 AudioInputStream audioInput = AudioSystem.getAudioInputStream(blaster);
				 Clip clip = AudioSystem.getClip();
				 clip.open(audioInput);
				 clip.start();
				 
				 
			 
			 }else {
				System.out.println("Inexistent File"); 
			 }
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
}
