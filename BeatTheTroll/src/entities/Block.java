package entities;

import animation.BlockAnimation;
import gameState.Level;
import math.Vec2;
import physics.Collidable;
import physics.Manifold;
import physics.SimplePhysicsData;

public class Block extends Entity implements Collidable{
	public Block(Level lev,Vec2 pos, int type){
		super(lev);
		anim = new BlockAnimation(this,type);
		ai = null;
		pd = new SimplePhysicsData(pos, new Vec2(32,32));
	}
	
	//public BlockTest(){
	//	super(null);
	//	anim = new TestAnimation(this, Color.RED);
	//	ai = null;
	//	pd = new SimplePhysicsData(new Vec2(300,500),new Vec2(32,32));
	//	//pd.cb = null;
	//}
	
	//public void update(){
	//	
	//}
	
	public void handleManifold(Manifold fold){
		
	}
	
	public void resetToDefaultAnimation(){
		//anim = new TestAnimation(this, Color.RED);
	}
}
