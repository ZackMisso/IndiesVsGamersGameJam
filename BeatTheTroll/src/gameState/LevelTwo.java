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
	private CollisionEngine cole;
	private int cloudTimer;
	private int bombTimer;
	private int levelTimer;
	
	public LevelTwo(GameStateManager g,Player p, Troll t){
		super(g,p,t);
		setBG(new Color(.2f,.4f,.8f));
		cloudTimer = 200;
		bombTimer = 92 - 12 * getTroll().getLevel();
		levelTimer = 2000;
		cole = new CollisionEngine();
	}
	
	public void init(){
		getTroll().reset();
		((TrollAI)getTroll().ai).setChances(2);
		getPlayer().switchToFalling();
		addInputAI((ReactToInputAI)getPlayer().ai);
		getPlayer().pd.pos = new Vec2(420,300);
		addEntity(new InvisEntity(this,new Vec2(80-40,121),new Vec2(40,480)));
		addEntity(new InvisEntity(this,new Vec2(800,121),new Vec2(40,480)));
		addEntity(new InvisEntity(this, new Vec2(80,130),new Vec2(800,40)));
		addEntity(new InvisEntity(this, new Vec2(80,540),new Vec2(800,40)));
		cole = new CollisionEngine();
	}
	
	public void update(){
		levelTimer--;
		if(levelTimer <=0)
			getGSM().transition();
		cloudTimer--;
		if(cloudTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 600 + 80, 700);
			Vec2 v = new Vec2(0,-1.5f);
			CloudAnimation c = new CloudAnimation(getGSM());
			LimitAnimation a = new LimitAnimation(c,p, 400, true);
			a.vel = v;
			addExtraAnimation(a);
			cloudTimer = 250;
		}
		bombTimer--;
		if(bombTimer <= 0){
			Vec2 p = new Vec2(RNG.getNextFloat() * 680 + 80, 700);
			Vec2 v = new Vec2(0,-2.2f);
			Bomb b = new Bomb(this,p,v);
			b.onTimer = true;
			b.timeToExplode = 240 + (int)(RNG.getNextFloat() * 20);
			addEntity(b);
			bombTimer = 55 - 12 * getTroll().getLevel();
		}
		getTroll().update();
		getPlayer().update();
		updateCollisionEngine();
		updateEntities();
		updateExtraAnimations();
		updateEntitiesToRemove();
		if(getRestart())
			getGSM().restart();
	}
}
