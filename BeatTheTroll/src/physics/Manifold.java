// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import entities.Entity;
import math.Vec2;

public class Manifold {
	public Entity one;
	public Entity two;
	public Vec2 norm;
	public Vec2 pen;
	
	public Manifold(Entity a, Entity b){
		one = a;
		two = b;
		norm = new Vec2();
		pen = new Vec2();
	}
}
