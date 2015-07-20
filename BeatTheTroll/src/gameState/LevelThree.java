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
import physics.CollisionEngine;

public class LevelThree extends Level{
	public CollisionEngine cole;
	public int transitionTimer;
	public boolean holeOpen;
	
	public LevelThree(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		//bg = Color.cyan;
		bg = new Color(.2f,.3f,.5f);
		transitionTimer = 30*60 * t.level; // one/two/three/four minute
		holeOpen = false;
		cole=new CollisionEngine();
	}
	
	public void init(){
		//PlayerTest play = new PlayerTest();
		//gameEntities.add(play);
		//for(int i=0;i<15;i++){
		//	Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
		//	gameEntities.add(block);
		//}
		//aiInput.add((ReactToInputAI)play.ai);
		//gameEntities.add(new Hammer(this,new Vec2(400,400),false));
		troll.reset();
		((TrollAI)troll.ai).setChances(3);
		player.pd.pos = new Vec2(300,400);
		player.switchToStanding();
		for(int i=0;i<23;i++)
			gameEntities.add(new Block(this,new Vec2(81 + i*32,536),1));
		for(int i=0;i<23;i++)
			gameEntities.add(new Block(this,new Vec2(81 + i*32,568),1));
		gameEntities.add(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		aiInput.add((ReactToInputAI)player.ai);
		cole = new CollisionEngine();
	}
	
	public void update(){
		if(!holeOpen)
			troll.update();
		//troll.update();
		player.update();
		cole.update(player, troll, gameEntities);
		//if(holeOpen){
		//	
		//}else{
			transitionTimer--;
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
			if(transitionTimer == 0){
				gameEntities.add(new Hole(this,new Vec2(81,121),true));
				//System.out.println("HOLE");
				holeOpen = true;
			}
			//cole.update(gameEntities.get(0), null, gameEntities);
		//}
			if(restart)
				gsm.restart();
	}
}
