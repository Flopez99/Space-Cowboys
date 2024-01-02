package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Space Cowboys";
	private static final long serialVersionUID = 1L;
	
	private static Game game;
	
	private boolean running = false;
	private Thread thread;
	
	
	private BufferedImage player;
	
	private BufferedImage spriteSheet = null; 
	private BufferedImage bullet = null;
	private BufferedImage background = null;
	private BufferedImage Menu = null;
	private BufferedImage Death = null;
	private BufferedImage Galaxy = null;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private SpriteSheet bullets;
	private boolean isShooting = false;
	
	private int enemy_count = 5;
	private int enemy_killed = 0;
	public int tot_enemy_killed = 0;
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private Death death;
	
	public LinkedList<EntityA> ea; 
	public LinkedList<EntityB> eb; 
	
	public static enum STATE{
		MENU,GAME,DEATH;
	}
	public static STATE state = STATE.MENU;
	
	public static int Health = 200;
	public static int GalaxyHealth = 1000;
	
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			spriteSheet = loader.loadImage("/Sprite_Sheet.png");
			background = loader.loadImage("/Background.png");
			Menu = loader.loadImage("/Menu.png");
			Death = loader.loadImage("/sad.png");
			Galaxy = loader.loadImage("/GalaxyConquered.png");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		
		c = new Controller(tex, this);
		p = new Player(200, 200 ,tex,this, c );
		menu = new Menu();
		death = new Death();
		Health = 200;
		GalaxyHealth = 1000;
		enemy_count = 5;
		enemy_killed = 0;
		tot_enemy_killed = 0;
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInp());
		
		c.createEnemy(enemy_count);
	}

	private synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1 ){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0; 
			}
			
		}
		stop();
	}
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
	
		g.drawImage(Menu, 0, 0, null);
		
		
		
		if(state == STATE.GAME) {
			
			g.drawImage(background, 0 , 0, null);
			p.render(g);
			c.render(g);
			
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 20);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, Health, 20);
			
			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 20);
			

			
			g.setColor(Color.gray);
			g.fillRect(0 , (Game.HEIGHT * Game.SCALE) - 10, 680, 23);
			
			g.setColor(Color.MAGENTA);
			g.fillRect(0 ,(Game.HEIGHT * Game.SCALE) - 10 , GalaxyHealth, 23);
			
			g.setColor(Color.white);
			g.drawRect(0 , (Game.HEIGHT * Game.SCALE) - 10, 653, 23);
			
			Graphics2D g2d = (Graphics2D) g;
			
			Font fnt0 = new Font("Arial", Font.BOLD, 15);
			Rectangle  Score = new Rectangle(Game.WIDTH + 165 , 0, 180, 50);
			g.setColor(Color.black);
			g.fillRect(Game.WIDTH + 165 , 0, 180, 50);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("Enemies Killed = " + tot_enemy_killed, Score.x , Score.y + 30);
			g2d.draw(Score);
		
			
		} else if(state == STATE.MENU) {
			menu.render(g);
		} 
		
		  else if(state == STATE.DEATH) {
			  
			if(Health == 0) {  
			g.drawImage(Death, 0 , 0, null);
			}
			else if(GalaxyHealth == 0) {
				g.drawImage(Galaxy, 0, 0, null);
			}
			death.render(g);
			
		}
		
		
	
		g.setColor(Color.gray);
		g.fillRect(10, -10, 150, -50);

		g.dispose();
		bs.show();
		
	}

	private void tick() {
	
		if (state == STATE.GAME) {
			
			p.tick();
			c.tick();
		}
		
		if(enemy_killed >= enemy_count) {
		
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(state == STATE.GAME) {
		if (key == KeyEvent.VK_RIGHT)  {
			p.setVelX(7);
		}if(key == KeyEvent.VK_LEFT) {
			p.setVelX(-7);
		}if(key == KeyEvent.VK_UP) {
			p.setVelY(-7);
		}if(key == KeyEvent.VK_DOWN) {
			p.setVelY(7);
		}if(key == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true;
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, c, this));
			
			String filepath = "BlasterSound.wav";
			Sounds blasterSound = new Sounds();
			
			blasterSound.playSound(filepath);
			}	
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT ){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT ) {
			p.setVelX(0);
		}else if(key == KeyEvent.VK_UP) {
			p.setVelY(0);
		}else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}else if(key == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
	}

	public static void main (String[]args) {
		
		game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame window = new JFrame(game.TITLE);
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		String filepath = "Music.wav";
		Sounds music= new Sounds();
		 
		music.playSound(filepath);
	
		game.start();
		
	}
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
		
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public int getEnemy_count() {
		return enemy_count;
	}


	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}


	public int getEnemy_killed() {
		return enemy_killed;
	}
	public int getTot_Enemy_Killed() {
		return tot_enemy_killed;
	}
	public void setTot_Enemy_Killed(int tot_enemy_killed) {
		this.tot_enemy_killed = tot_enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public static Game getGame() {
		return game;
	}
}
