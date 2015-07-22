// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import animation.BlockAnimation;
import gameState.Level;
import math.Vec2;
import physics.Collidable;
import physics.Manifold;
import physics.SimplePhysicsData;

public class Block extends Entity implements Collidable{
	public Block(Level lev,Vec2 pos, int type){
		super(lev,new BlockAnimation(this,type),pos,new Vec2(),null);
	}
	
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
