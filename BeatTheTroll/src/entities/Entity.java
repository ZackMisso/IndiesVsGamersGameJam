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
	public Animation anim;
	public PhysicsData pd;
	public AI ai;
	public Level ref;
	public int damage;
	public boolean harmfulToP;
	public boolean alive;
	public boolean collidingRight;
	public boolean collidingLeft;
	public boolean collidingUp;
	public boolean collidingDown;
	public boolean jumping;
	public boolean onGround;
	public boolean jumpInAir;
	
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
		//if(pd == null)
		//	System.out.println("PD is null");
		//if(pd.pos == null)
		//	System.out.println("Pos is null");
		if(ref!=null)
			anim.draw(g, pd.pos.add(ref.offset.neg(true),false));
		else
			anim.draw(g, pd.pos);
	}
	
	public abstract void handleManifold(Manifold fold);
	public abstract void resetToDefaultAnimation();
}
