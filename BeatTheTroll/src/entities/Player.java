// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import java.awt.Color;

import ai.PlayerAI;
import ai.PlayerFallingAI;
import gameState.GameStateManager;
import math.Vec2;
import physics.Collidable;
import physics.DynamicPhysicsData;
import physics.Manifold;
import tests.TestAnimation;

public class Player extends Entity{
	private GameStateManager gsmRef;
	private int invulnerableTimer;
	private boolean invulnerable;
	private boolean flip;
	private boolean collidingRight;
	private boolean collidingLeft;
	private boolean collidingUp;
	private boolean collidingDown;
	private boolean jumping;
	
	public Player(GameStateManager ref){
		super(null);
		gsmRef = ref;
		anim = new TestAnimation(this, Color.BLUE);
		ai = new PlayerAI(this);
		pd = new DynamicPhysicsData(new Vec2(300,400),new Vec2(32,32));
		DynamicPhysicsData tmp = (DynamicPhysicsData)pd;
		tmp.maxVel = new Vec2(3.0f,5.0f);
		tmp.acc = new Vec2(0,.1f);
		invulnerableTimer = 0;
		invulnerable = false;
		flip = true;
		jumping = false;
	}
	
	public void clearCollides(){
		collidingRight=false;
		collidingLeft=false;
		collidingUp=false;
		collidingDown=false;
	}
	
	public void update(){
		super.update();
		clearCollides();
		if(invulnerable){
			invulnerableTimer--;
			if(invulnerableTimer <= 0){
				invulnerable = false;
			}
		}
	}
	
	public void switchToFalling(){
		ai = new PlayerFallingAI(this);
		DynamicPhysicsData tmp = (DynamicPhysicsData)pd;
		tmp.acc = new Vec2();
		tmp.vel = new Vec2();
	}
	
	public void switchToStanding(){
		ai = new PlayerAI(this);
		DynamicPhysicsData tmp = (DynamicPhysicsData)pd;
		tmp.acc = new Vec2(0,.1f);
		tmp.vel = new Vec2();
	}
	
	public void handleManifold(Manifold fold){
		if(fold.two instanceof Collidable){
			if(fold.getNorm().getX() != 0){
				fold.pen.x *= fold.norm.x;
				fold.pen.x += fold.norm.x;
				if(fold.norm.x > 0)
					collidingLeft = true;
				else
					collidingRight = true;
				((DynamicPhysicsData)pd).vel.x = 0;
			}
			if(fold.norm.y != 0){
				fold.pen.y *= fold.norm.y;
				fold.pen.y += fold.norm.y;
				if(fold.norm.y > 0)
					collidingUp = true;
				else{
					collidingDown = true;
					jumping = false;
				}
				((DynamicPhysicsData)pd).vel.y = 0;
			}
			pd.pos.add(fold.pen);
		}
	}
	
	public void resetToDefaultAnimation(){
		anim = new TestAnimation(this, Color.BLUE);
	}
	
	public void nowInvulnerable(int time){
		invulnerable = true;
		invulnerableTimer = time;
	}
	
	// getter methods
	public boolean getInvulnerable() {return invulnerable;}
	public boolean getCollidingRight(){return collidingRight;}
	public boolean getCollidingLeft(){return collidingLeft;}
	public boolean getCollidingUp(){return collidingUp;}
	public boolean getCollidingDown(){return collidingDown;}
	public boolean getJumping(){return jumping;}
	
	// setter methods
	public void setCollidingRight(boolean param){collidingRight = param;}
	public void setCollidingLeft(boolean param){collidingLeft = param;}
	public void setCollidingUp(boolean param){collidingUp=param;}
	public void setCollidingDown(boolean param){collidingDown=param;}
	public void setJumping(boolean param){jumping=param;}
}
