// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import java.awt.Graphics2D;

import ai.AI;
import animation.Animation;
import art.ImageParser;
import gameState.Level;
import math.Vec2;
import physics.DynamicPhysicsData;
import physics.Manifold;
import physics.PhysicsData;
import physics.SimplePhysicsData;

public abstract class Entity {
	private Animation anim;
	private PhysicsData pd;
	private AI ai;
	private Level ref;
	private int damage;
	private boolean harmfulToP;
	private boolean alive;
	private boolean onGround;
	private boolean jumpInAir;
	
	public Entity(Level param){
		anim = null;
		pd = null;
		ai = null;
		ref = param;
		harmfulToP = false;
		alive = true;
		jumpInAir = false;
		onGround = false;
		damage = 0;
	}
	
	public Entity(Level l, Animation a, PhysicsData data, AI i){
		ref = l;
		ai = i;
		anim = a;
		pd = data;
		harmfulToP = false;
		alive = true;
		jumpInAir = false;
		onGround = false;
		damage = 0;
	}
	
	public Entity(Level l, Animation a, Vec2 pos, Vec2 siz, AI i){
		ref = l;
		pd = new SimplePhysicsData(pos, siz);
		anim = a;
		ai = i;
		harmfulToP = false;
		alive = true;
		jumpInAir = false;
		onGround = false;
		damage = 0;
	}
	
	public boolean outOfBounds(){
		if(pd.getPos().y > 121 + 478)
			return true;
		if(pd.getPos().y + pd.getSiz().y < 121)
			return true;
		if(pd.getPos().x > 81 + 718)
			return true;
		if(pd.getPos().x + pd.getSiz().x < 81)
			return true;
		return false;
	}
	
	public void setPos(Vec2 p){
		pd.setPos(p);
	}
	
	public void update(){
		if(ai != null)
			ai.update();
		if(anim != null)
			anim.update();
		pd.update();
	}
	
	public void draw(Graphics2D g){
		anim.draw(g, pd.getPos());
	}
	
	public void updateAnimation(){
		anim.update();
	}
	
	public void createDynamicPhysics(Vec2 pos,Vec2 siz,Vec2 vel,Vec2 acc){
		DynamicPhysicsData data = new DynamicPhysicsData(pos,siz);
		data.setVel(vel);
		data.setAcc(acc);
		pd = data;
	}
	
	public void setDead(){
		alive = false;
	}
	
	public void initForLevel(Level level){
		ref = level;
	}
	
	public ImageParser getParser(){
		return ref.getGSM().getParser();
	}
	
	public abstract void handleManifold(Manifold fold);
	public abstract void resetToDefaultAnimation();
	
	// getter methods
	public Level getRef(){return ref;}
	public Animation getAnim() {return anim;}
	public PhysicsData getPD(){return pd;}
	public AI getAI(){return ai;}
	public int getDamage(){return damage;}
	
	// setter methods
	public void setRef(Level param){ref=param;}
	public void setAnim(Animation param){anim=param;}
	public void setPD(PhysicsData param){pd=param;}
	public void setAI(AI param){ai=param;}
	public void setHarmfulToP(boolean param){harmfulToP = param;}
	public void setDamage(int param){damage=param;}
}
