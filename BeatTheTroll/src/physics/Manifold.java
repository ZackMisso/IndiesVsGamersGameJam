package physics;

import entities.Entity;
import math.Vec2;

public class Manifold {
	public Entity one;
	public Entity two;
	public Vec2 norm;
	public Vec2 pen;
	//public Vec2 depth;
	
	public Manifold(Entity a, Entity b){
		one = a;
		two = b;
		norm = new Vec2();
		pen = new Vec2();
		//depth = new Vec2();
	}
}
