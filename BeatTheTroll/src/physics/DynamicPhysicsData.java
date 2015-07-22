// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import math.Vec2;

public class DynamicPhysicsData extends PhysicsData{
	private Vec2 vel;
	private Vec2 maxVel;
	private Vec2 acc;
	//private CollisionBox cb;
	
	public DynamicPhysicsData(Vec2 pos, Vec2 siz){
		super(pos, siz);
		vel = new Vec2();
		maxVel = new Vec2(5.0f,5.0f);
		acc = new Vec2();
		//cb = null;
	}
	
	public void update(){
		getPos().add(vel);
		vel.add(acc);
		if(vel.x > maxVel.x)
			vel.x = maxVel.x;
		if(vel.y > maxVel.y)
			vel.y = maxVel.y;
		if(vel.x < -maxVel.x)
			vel.x = -maxVel.x;
		if(vel.y < -maxVel.y)
			vel.y = -maxVel.y;
	}
	
	// getter methods
	public Vec2 getVel(){return vel;}
	public Vec2 getMaxVel(){return maxVel;}
	public Vec2 getAcc(){return acc;}
	
	// setter methods'
	public void setVel(Vec2 param){vel = param;}
	public void setMaxVel(Vec2 param){maxVel = param;}
	public void setAcc(Vec2 param){acc = param;}
}
