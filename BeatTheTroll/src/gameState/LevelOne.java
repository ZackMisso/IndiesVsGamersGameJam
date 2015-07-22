// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

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
	private int hammerTimer;
	
	public LevelOne(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		setBG(Color.DARK_GRAY);
		hammerTimer = 50 - 10 * getTroll().getLevel();
	}
	
	public void init(){
		getTroll().reset();
		((TrollAI)getTroll().ai).setChances(1);
		getPlayer().pd.pos = new Vec2(300,400);
		getPlayer().switchToStanding();
		for(int i=0;i<15;i++){
			Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
			addEntity(block);
		}
		aiInput.add((ReactToInputAI)player.ai);
		gameEntities.add(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		cole = new CollisionEngine();
	}
	
	public void update(){
		if(GlobalController.gameOver == true)
			return;
		hammerTimer--;
		GlobalController.timeTillAttack--;
		if(GlobalController.timeTillAttack <= 0){
			if(getTroll().getLevel() == 1){
				GlobalController.initToTwo();
				GlobalController.level = 2;
				getTroll().changeToTwo();
				addEntity(new Hole(this,new Vec2(80,480),false));
			}
			else if(getTroll().getLevel() == 2){
				GlobalController.initToThree();
				getTroll().changeToThree();
				GlobalController.level = 3;
				addEntity(new Hole(this,new Vec2(80,480),false));
				
			}else if(getTroll().getLevel() == 3){
				GlobalController.initToFour();
				getTroll().changeToFour();
				GlobalController.level = 4;
				addEntity(new Hole(this,new Vec2(80,700),false));
			}else if(getTroll().getLevel() == 4){
				GlobalController.level = 4;
				GlobalController.gameOver = true;
				GlobalController.addHighScore();
			}
		}
		if(hammerTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 680 + 80,90);
			addEntity(new Hammer(this,p));
			hammerTimer = 50 - 10 * getTroll().getLevel();
		}
		getTroll().update();
		getPlayer().update();
		if(getPlayer().outOfBounds())
			getGSM().transition();
		updateCollisionEngine();
		updateEntities();
		updateExtraAnimations();
		updateEntitiesToRemove();
		if(getRestart())
			getGSM().restart();
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
