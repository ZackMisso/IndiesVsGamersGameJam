// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import math.Vec2;

// This class was never used

public class CollisionBox {
	private float reducMinx;
	private float reducMaxx;
	private float reducMiny;
	private float reducMaxy;
	
	public CollisionBox(){
		reducMinx = 0;
		reducMaxx = 0;
		reducMiny = 0;
		reducMaxy = 0;
	}
	
	public CollisionBox(float a, float b, float c, float d){
		reducMinx = a;
		reducMaxx = b;
		reducMiny = c;
		reducMaxy = d;
	}
	
	public Vec2 getNewOrigin(Vec2 origin){
		return new Vec2(origin.x - reducMinx,origin.y - reducMiny);
	}
	
	public Vec2 getNewDimension(Vec2 dim){
		return new Vec2(dim.x - reducMinx - reducMaxx, dim.y - reducMiny - reducMaxy);
	}
	
	public Vec2 getMidpoint(Vec2 origin,Vec2 dim){
		Vec2 a = getNewOrigin(origin);
		Vec2 b = getNewDimension(dim);
		return new Vec2(a.x + b.x / 2,a.y + b.y / 2);
	}
	
	// getter methods
	public float getReducMinX(){return reducMinx;}
	public float getReducMaxX(){return reducMaxx;}
	public float getReducMinY(){return reducMiny;}
	public float getReducMaxY(){return reducMaxy;}
	
	// setter methods
	public void setReducMinX(float param){reducMinx=param;}
	public void setReducMaxX(float param){reducMaxx=param;}
	public void setReducMinY(float param){reducMiny=param;}
	public void setReducMaxY(float param){reducMaxy=param;}
}
