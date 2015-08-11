// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

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
import misc.PlayerStatus;
import misc.RNG;

public class LevelOne extends Level{
	private int hammerTimer;
	
	public LevelOne(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		setBG(Color.DARK_GRAY);
		hammerTimer = 50 - 10 * getTroll().getLevel();
	}
	
	public void init(){
		getTroll().reset();
		((TrollAI)getTroll().getAI()).setChances(1);
		getPlayer().getPD().setPos(new Vec2(300,400));
		getPlayer().switchToStanding();
		for(int i=0;i<15;i++){
			Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
			addEntity(block);
		}
		addInputAI((ReactToInputAI)getPlayer().getAI());
		addEntity(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		addEntity(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
	}
	
	public void update(){
		PlayerStatus status = getGSM().getStatus();
		if(status.getGameOver())
			return;
		hammerTimer--;
		status.decrementTimeTillAttack();
		if(status.getTimeTillAttack() <= 0){
			if(getTroll().getLevel() == 1){
				status.initToTwo();
				status.setLevel(2);
				getTroll().changeToTwo();
				addEntity(new Hole(this,new Vec2(80,480),false));
			}
			else if(getTroll().getLevel() == 2){
				status.initToThree();
				getTroll().changeToThree();
				status.setLevel(3);
				addEntity(new Hole(this,new Vec2(80,480),false));
				
			}else if(getTroll().getLevel() == 3){
				status.initToFour();
				getTroll().changeToFour();
				status.setLevel(4);
				addEntity(new Hole(this,new Vec2(80,700),false));
			}else if(getTroll().getLevel() == 4){
				status.setLevel(4);
				status.setGameOver(true);
				GlobalController.addHighScore(status.getScore());
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
		if(getGSM().getStatus().getGameOver()){
			g.setColor(Color.BLACK);
			g.fillRect(150, 180, 560, 300);
			g.setColor(Color.GREEN);
			g.drawString("Thank You for Playing!", 340, 250);
			g.drawString("Congratulations you beat the Troll and managed to keep "+getGSM().getStatus().getScore()+" Points", 200, 300);
			g.drawString("Press 'r' to restart the game and try to go for a higher score", 240, 350);
		}
	}
}
