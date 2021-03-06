// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import math.Vec2;

public abstract class PhysicsData {
	private Vec2 pos;
	private Vec2 siz;
	
	PhysicsData(Vec2 a, Vec2 b){
		pos = a;
		siz = b;
	}
	
	public Vec2 cent(){
		return new Vec2(pos.x + siz.x/2, pos.y + siz.y/2);
	}
	
	public abstract void update();
	
	// getter methods
	public Vec2 getPos(){return pos;}
	public Vec2 getSiz(){return siz;}
	
	// setter methods
	public void setPos(Vec2 param){pos=param;}
	public void setSiz(Vec2 param){siz=param;}
}
