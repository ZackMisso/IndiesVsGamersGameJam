// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;

// transitioning level

import ai.ReactToInputAI;
import ai.TrollAI;
import animation.CloudAnimation;
import animation.LimitAnimation;
import entities.Bomb;
import entities.InvisEntity;
import entities.Player;
import entities.Troll;
import math.Vec2;
import misc.RNG;
import physics.CollisionEngine;

public class LevelTwo extends Level{
	CollisionEngine cole;
	public int cloudTimer;
	public int bombTimer;
	public int levelTimer;
	
	public LevelTwo(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		bg = Color.DARK_GRAY;
		bg = new Color(.2f,.4f,.8f);
		cloudTimer = 200;
		bombTimer = 92 - 12 * troll.level;
		levelTimer = 2000;
		cole = new CollisionEngine();
	}
	
	public void init(){
		//PlayerTest play = new PlayerTest();
		//gameEntities.add(play);
		//for(int i=0;i<15;i++){
		//	Block block = new Block(this,new Vec2(200 + 32 * i,500),2);
		//	gameEntities.add(block);
		//}
		troll.reset();
		((TrollAI)troll.ai).setChances(2);
		player.switchToFalling();
		aiInput.add((ReactToInputAI)player.ai);
		player.pd.pos = new Vec2(420,300);
		//player.switchToFalling();
		gameEntities.add(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		gameEntities.add(new InvisEntity(this, new Vec2(80,130),new Vec2(800,40)));
		gameEntities.add(new InvisEntity(this, new Vec2(80,540),new Vec2(800,40)));
		//gameEntities.add(new Hammer(this,new Vec2(400,400),false));
		cole = new CollisionEngine();
	}
	
	public void update(){
		levelTimer--;
		if(levelTimer <=0){
			gsm.transition();
		}
		cloudTimer--;
		if(cloudTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 600 + 80, 700);
			Vec2 v = new Vec2(0,-1.5f);
			CloudAnimation c = new CloudAnimation(gsm);
			LimitAnimation a = new LimitAnimation(c,p, 400, true);
			a.vel = v;
			extraAnimations.add(a);
			cloudTimer = 250;
		}
		bombTimer--;
		if(bombTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 680 + 80, 700);
			Vec2 v = new Vec2(0,-2.2f);
			Bomb b = new Bomb(this,p,v);
			b.onTimer = true;
			b.timeToExplode = 240 + (int)(RNG.getNextFloat() * 20);
			gameEntities.add(b);
			bombTimer = 55 - 12 * troll.level;//troll.level;
		}
		troll.update();
		player.update();
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
		if(restart)
			gsm.restart();
	}
}
