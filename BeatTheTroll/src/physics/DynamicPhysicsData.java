// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import math.Vec2;

public class DynamicPhysicsData extends PhysicsData{
	public Vec2 vel;
	public Vec2 maxVel;
	public Vec2 acc;
	public CollisionBox cb;
	
	public DynamicPhysicsData(Vec2 pos, Vec2 siz){
		super(pos, siz);
		vel = new Vec2();
		maxVel = new Vec2(5.0f,5.0f);
		acc = new Vec2();
		cb = null;
	}
	
	public void update(){
		pos.add(vel);
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
}
