// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import java.awt.Graphics2D;

import ai.AI;
import animation.Animation;
import gameState.Level;
import math.Vec2;
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
	private boolean jumping;
	private boolean onGround;
	private boolean jumpInAir;
	
	public Entity(Level param){
		anim = null;
		pd = null;
		ai = null;
		ref = param;
		harmfulToP = false;
		alive = true;
		jumping = false;
		jumpInAir = false;
		onGround = false;
		clearCollides();
	}
	
	public Entity(Level l, Animation a, PhysicsData data, AI i){
		ref = l;
		ai = i;
		anim = a;
		pd = data;
		harmfulToP = false;
		alive = true;
		jumping = false;
		jumpInAir = false;
		onGround = false;
		clearCollides();
	}
	
	public Entity(Level l, Animation a, Vec2 pos, Vec2 siz, AI i){
		ref = l;
		pd = new SimplePhysicsData(pos, siz);
		anim = a;
		ai = i;
		harmfulToP = false;
		alive = true;
		jumping = false;
		jumpInAir = false;
		onGround = false;
		clearCollides();
	}
	
	public void clearCollides(){
		collidingRight = false;
		collidingLeft = false;
		collidingUp = false;
		collidingDown = false;
	}
	
	public boolean outOfBounds(){
		if(pd.pos.y > 121 + 478)
			return true;
		if(pd.pos.y + pd.siz.y < 121)
			return true;
		if(pd.pos.x > 81 + 718)
			return true;
		if(pd.pos.x + pd.siz.x < 81)
			return true;
		return false;
	}
	
	public void setPos(Vec2 p){
		pd.pos = p;
	}
	
	public void update(){
		clearCollides();
		if(ai != null)
			ai.update();
		if(anim != null)
			anim.update();
		pd.update();
	}
	
	public void draw(Graphics2D g){
		if(ref!=null)
			anim.draw(g, pd.pos.add(ref.offset.neg(true),false));
		else
			anim.draw(g, pd.pos);
	}
	
	public void updateAnimation(){
		anim.update();
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
	
	// setter methods
	public void setHarmfulToP(boolean param){harmfulToP = param;}
}
