// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;

// Grass Level

import ai.ReactToInputAI;
import ai.TrollAI;
import entities.Block;
import entities.Hole;
import entities.InvisEntity;
import entities.Player;
import entities.Troll;
import math.Vec2;

public class LevelThree extends Level{
	private int transitionTimer;
	private boolean holeOpen;
	
	public LevelThree(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		setBG(new Color(.2f,.3f,.5f));
		transitionTimer = 30*60 * t.getLevel(); // one/two/three/four minute
		holeOpen = false;
	}
	
	public void init(){
		getTroll().reset();
		((TrollAI)getTroll().getAI()).setChances(3);
		getPlayer().getPD().setPos(new Vec2(300,400));
		getPlayer().switchToStanding();
		for(int i=0;i<23;i++)
			addEntity(new Block(this,new Vec2(81 + i*32,536),1));
		for(int i=0;i<23;i++)
			addEntity(new Block(this,new Vec2(81 + i*32,568),1));
		addEntity(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		addEntity(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		addInputAI((ReactToInputAI)getPlayer().getAI());
	}
	
	public void update(){
		if(!holeOpen)
			getTroll().update();
		getPlayer().update();
		updateCollisionEngine();
		updateEntities();
		updateExtraAnimations();
		updateEntitiesToRemove();
		transitionTimer--;
		if(transitionTimer == 0){
			addEntity(new Hole(this,new Vec2(81,121),true));
			holeOpen = true;
		}
		if(getRestart())
			getGSM().restart();
	}
}
