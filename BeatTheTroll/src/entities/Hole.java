// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import animation.HoleAnimation;
import gameState.Level;
import math.Vec2;
import physics.DynamicPhysicsData;
import physics.Manifold;
import physics.SimplePhysicsData;

public class Hole extends Entity{
	public Hole(Level ref, Vec2 pos, boolean sideways){
		super(ref);
		if(sideways){
			anim = new HoleAnimation(this,sideways);
			pd = new SimplePhysicsData(pos, new Vec2(40,580));
		}else{
			anim = new HoleAnimation(this,sideways);
			pd = new DynamicPhysicsData(pos, new Vec2(780,40));
			((DynamicPhysicsData)pd).vel.y = -2;
		}
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getOne() instanceof Player)
			getRef().getGSM().transition();
	}
	
	public void resetToDefaultAnimation(){}
}
