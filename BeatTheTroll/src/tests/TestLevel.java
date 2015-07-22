// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package tests;

import java.awt.Color;

import ai.ReactToInputAI;
import entities.Blast;
import entities.Block;
import entities.Bomb;
import entities.Hammer;
import entities.InvisEntity;
import entities.Player;
import entities.Troll;
import gameState.GameStateManager;
import gameState.Level;
import math.Vec2;
import physics.CollisionEngine;

public class TestLevel extends Level{
	private CollisionEngine cole;
	private int timer;
	private int timer2;
	private int timer3;
	
	public TestLevel(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		//bg = Color.DARK_GRAY;
		bg = new Color(.2f,.3f,.5f);
		timer = 100;
		timer2 = 160;
		timer3 = 400;
	}
	
	public void init(){
		PlayerTest play = new PlayerTest();
		gameEntities.add(play);
		play.pd.pos = new Vec2(420,260);
		for(int i=0;i<13;i++){
			Block block = new Block(this,new Vec2(232 + 32 * i,500),2);
			gameEntities.add(block);
		}
		gameEntities.add(new InvisEntity(this,new Vec2(120-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		//for(int i=0;i<23;i++)
		//	gameEntities.add(new Block(this,new Vec2(81 + i*32,536),1));
		//for(int i=0;i<23;i++)
		//	gameEntities.add(new Block(this,new Vec2(81 + i*32,568),1));
		aiInput.add((ReactToInputAI)play.ai);
		gameEntities.add(new Hammer(this,new Vec2(400,400),false));
		gameEntities.add(new InvisEntity(this,new Vec2(120-40,121),new Vec2(40,480)));
		cole = new CollisionEngine();
	}
	
	public void update(){
		timer--;
		if(timer == 0){
			gameEntities.add(new Hammer(this,new Vec2(400,200),false));
			timer = 100;
		}
		timer2--;
		if(timer2 == 0){
			gameEntities.add(new Blast(this,new Vec2(200,400),false));
			timer2 = 160;
		}
		timer3--;
		if(timer3 == 0){
			gameEntities.add(new Bomb(this,new Vec2(300,450),new Vec2()));
			timer3 = 200;
		}
		troll.update();
		cole.update(gameEntities.get(0), null, gameEntities);
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
	}
}
