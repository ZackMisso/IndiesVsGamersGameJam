package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

// This is the main fight level

import ai.ReactToInputAI;
import ai.TrollAI;
import entities.Block;
import entities.Hammer;
import entities.Hole;
import entities.InvisEntity;
import entities.Player;
import entities.Troll;
import math.Vec2;
import misc.GlobalController;
import misc.RNG;
import physics.CollisionEngine;

public class LevelOne extends Level{
	CollisionEngine cole;
	public int hammerTimer;
	//public boolean gameOver;
	//public boolean restart;
	
	public LevelOne(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		bg = Color.DARK_GRAY;
		hammerTimer = 50 - 10 * troll.level;
		cole = new CollisionEngine();
		//gameOver = false;
		//restart = false;
	}
	
	public void init(){
		//PlayerTest play = new PlayerTest();
		//gameEntities.add(play);
		//for(int i=0;i<15;i++){
		//	Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
		//	gameEntities.add(block);
		//}
		troll.reset();
		((TrollAI)troll.ai).setChances(1);
		player.pd.pos = new Vec2(300,400);
		player.switchToStanding();
		for(int i=0;i<15;i++){
			Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
			gameEntities.add(block);
		}
		aiInput.add((ReactToInputAI)player.ai);
		gameEntities.add(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		//gameEntities.add(new Hammer(this,new Vec2(400,400),false));
		cole = new CollisionEngine();
	}
	
	public void update(){
		if(GlobalController.gameOver == true)
			return;
		//GlobalController.gameOver = true;
		hammerTimer--;
		GlobalController.timeTillAttack--;
		if(GlobalController.timeTillAttack <= 0){
			if(troll.level == 1){
				GlobalController.initToTwo();
				GlobalController.level = 2;
				troll.changeToTwo();
				gameEntities.add(new Hole(this,new Vec2(80,480),false));
			}
			else if(troll.level == 2){
				GlobalController.initToThree();
				troll.changeToThree();
				GlobalController.level = 3;
				gameEntities.add(new Hole(this,new Vec2(80,480),false));
				
			}else if(troll.level == 3){
				GlobalController.initToFour();
				troll.changeToFour();
				GlobalController.level = 4;
				gameEntities.add(new Hole(this,new Vec2(80,700),false));
			}else if(troll.level == 4){
				GlobalController.level = 4;
				GlobalController.gameOver = true;
				GlobalController.addHighScore();
				//System.exit(0);
				//return;
			}
		}
		//System.out.println(gameEntities.size());
		if(hammerTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 680 + 80,90);
			gameEntities.add(new Hammer(this,p));
			hammerTimer = 50 - 10 * troll.level;
		}
		troll.update();
		player.update();
		if(player.outOfBounds())
			gsm.transition();
		cole.update(player, troll, gameEntities);
		for(int i=0;i<gameEntities.size();i++)
			gameEntities.get(i).update();
		for(int i=0;i<extraAnimations.size();i++){
			extraAnimations.get(i).update();
			if(extraAnimations.get(i).checkRemove())
				extraAnimations.remove(i--);
		}
		for(int i = 0;i<entitiesToRemove.size();i++){
			//System.out.println("REMOVING ENTITY");
			gameEntities.remove(entitiesToRemove.get(0));
			entitiesToRemove.remove(0);
		}
		//cole.update(gameEntities.get(0), null, gameEntities);
		if(restart){
			gsm.restart();
		}
	}
	
	public void draw(Graphics2D g){
		super.draw(g);
		if(GlobalController.gameOver){
			g.setColor(Color.BLACK);
			g.fillRect(150, 180, 560, 300);
			g.setColor(Color.GREEN);
			g.drawString("Thank You for Playing!", 340, 250);
			g.drawString("Congratulations you beat the Troll and managed to keep "+GlobalController.score+" Points", 200, 300);
			g.drawString("Press 'r' to restart the game and try to go for a higher score", 240, 350);
		}
	}
}
