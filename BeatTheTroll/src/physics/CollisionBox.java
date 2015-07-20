package physics;

import math.Vec2;

public class CollisionBox {
	//public Vec2 offset;
	public float reducMinx;
	public float reducMaxx;
	public float reducMiny;
	public float reducMaxy;
	
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
}
