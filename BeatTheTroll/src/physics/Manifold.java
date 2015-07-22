// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package physics;

import entities.Entity;
import math.Vec2;

public class Manifold {
	private Entity one;
	private Entity two;
	private Vec2 norm;
	private Vec2 pen;
	
	public Manifold(Entity a, Entity b){
		one = a;
		two = b;
		norm = new Vec2();
		pen = new Vec2();
	}
	
	// getter methods
	public Entity getOne(){return one;}
	public Entity getTwo(){return two;}
	public Vec2 getNorm(){return norm;}
	public Vec2 getPen(){return pen;}
	
	// setter methods
	public void setOne(Entity param){one = param;}
	public void setTwo(Entity param){two = param;}
	public void setNorm(Vec2 param){norm = param;}
	public void setPen(Vec2 param){pen = param;}
}
