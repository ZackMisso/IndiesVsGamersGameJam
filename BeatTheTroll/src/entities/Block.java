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
		super(null);
		setRef(lev);
		setAnim(new BlockAnimation(this,type));
		setPD(new SimplePhysicsData(pos,new Vec2()));
		setAI(null);
	}
	
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
