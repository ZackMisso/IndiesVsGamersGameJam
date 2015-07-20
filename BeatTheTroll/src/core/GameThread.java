package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameState.GameStateManager;
import misc.RNG;

public class GameThread extends JPanel implements Runnable {
	public Game game;
	public GameStateManager gsm;
	//public InitLevel lev;
	public static boolean running;
	//public boolean gameSet;
	
	public GameThread(Game param){
		game = param;
		RNG.init();
		gsm = new GameStateManager(game);
		//lev = new InitLevel();
		//RNG.init();
		running = true;
		//gameSet = false;
	}
	
	public void update(){
		//if(!gameSet){
		//	
		//}else
		gsm.update();
	}
	
	public void paint(Graphics g){
		BufferedImage backBuffer = new BufferedImage(880, 800, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)backBuffer.getGraphics();
		//if(!gameSet){
		//	
		//}else
			gsm.draw(g2);
		g.drawImage(backBuffer, 0, 0, game.window);
	}
	
	public void init(){
		RNG.init();
	}
	
	public void run(){
		//init();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0.0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while(delta >= 1){
				ticks++;
				update();
				delta -= 1;
				shouldRender = true;
			}
			if(shouldRender){
				frames++;
				paint(game.window.getGraphics());
			}
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				//System.out.println("FPS :: "+ticks+" Frames :: "+frames);
				if(ticks >= 55 && frames >= 50){
					RNG.extraTime();
					RNG.extraTime();
				}
				frames = 0;
				ticks = 0;
			}
		}
	}
}
