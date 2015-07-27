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
	private boolean collidingRight;
	private boolean collidingLeft;
	private boolean collidingUp;
	private boolean collidingDown;
	private boolean jumping;
	
	public Player(GameStateManager ref){
		super(null);
		gsmRef = ref;
		setAnim(new TestAnimation(this, Color.BLUE));
		setAI(new PlayerAI(this));
		setPD(new DynamicPhysicsData(new Vec2(300,400),new Vec2(32,32)));
		DynamicPhysicsData tmp = (DynamicPhysicsData)getPD();
		tmp.setMaxVel(new Vec2(3.0f,5.0f));
		tmp.setAcc(new Vec2(0,.1f));
		invulnerableTimer = 0;
		invulnerable = false;
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
		setAI(new PlayerFallingAI(this));
		DynamicPhysicsData tmp = (DynamicPhysicsData)getPD();
		tmp.setAcc(new Vec2());
		tmp.setVel(new Vec2());
	}
	
	public void switchToStanding(){
		setAI(new PlayerAI(this));
		DynamicPhysicsData tmp = (DynamicPhysicsData)getPD();
		tmp.setAcc(new Vec2(0,.1f));
		tmp.setVel(new Vec2());
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getTwo() instanceof Collidable){
			if(fold.getNorm().x != 0){
				fold.getPen().x *= fold.getNorm().x;
				fold.getPen().x += fold.getNorm().x;
				if(fold.getNorm().x > 0)
					collidingLeft = true;
				else
					collidingRight = true;
				((DynamicPhysicsData)getPD()).getVel().x = 0;
			}
			if(fold.getNorm().y != 0){
				fold.getPen().y *= fold.getNorm().y;
				fold.getPen().y += fold.getNorm().y;
				if(fold.getNorm().y > 0)
					collidingUp = true;
				else{
					collidingDown = true;
					jumping = false;
				}
				((DynamicPhysicsData)getPD()).getVel().y = 0;
			}
			getPD().getPos().add(fold.getPen());
		}
	}
	
	public void resetToDefaultAnimation(){
		setAnim(new TestAnimation(this, Color.BLUE));
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
