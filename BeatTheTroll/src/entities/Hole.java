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
			setAnim(new HoleAnimation(this,sideways));
			setPD(new SimplePhysicsData(pos, new Vec2(40,580)));
		}else{
			setAnim(new HoleAnimation(this,sideways));
			setPD(new DynamicPhysicsData(pos, new Vec2(780,40)));
			((DynamicPhysicsData)getPD()).getVel().y = -2;
		}
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getOne() instanceof Player)
			getRef().getGSM().transition();
	}
	
	public void resetToDefaultAnimation(){}
}
