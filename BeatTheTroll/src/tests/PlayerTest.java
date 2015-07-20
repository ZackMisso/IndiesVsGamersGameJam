package tests;

import java.awt.Color;

import entities.Entity;
import math.Vec2;
import physics.Collidable;
import physics.DynamicPhysicsData;
import physics.Manifold;

public class PlayerTest extends Entity{
	public PlayerTest(){
		super(null);
		anim = new TestAnimation(this, Color.BLUE);
		ai = new PlayerTestAI(this);
		//ai = new PlayerFallingTestAI(this);
		pd = new DynamicPhysicsData(new Vec2(300,400),new Vec2(32,32));
		DynamicPhysicsData tmp = (DynamicPhysicsData)pd;
		tmp.maxVel = new Vec2(3.0f,5.0f);
		tmp.acc = new Vec2(0,.1f);
		//pd.cb = null;
	}
	
	//public void update(){
	//	
	//}
	
	public void handleManifold(Manifold fold){
		//System.out.println("Pen "+fold.pen.toString());
		//System.out.println("Pos "+pd.pos.toString());
		if(fold.two instanceof Collidable){
			if(fold.norm.x != 0){
				fold.pen.x *= fold.norm.x;
				fold.pen.x += fold.norm.x;
				if(fold.norm.x > 0)
					collidingLeft = true;
				else
					collidingRight = true;
				((DynamicPhysicsData)pd).vel.x = 0;
				//((DynamicPhysicsData)pd).acc.x = 0;
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
				//((DynamicPhysicsData)pd).acc.y = 0;
			}
			pd.pos.add(fold.pen);
		}
		//System.out.println("Pos "+pd.pos.toString());
		//if(fold.pen.x != 0.0f && fold.pen.y != 0.0f){
		//	System.out.println("Resolving");
		//}
	}
	
	public void resetToDefaultAnimation(){
		anim = new TestAnimation(this, Color.BLUE);
	}
}
