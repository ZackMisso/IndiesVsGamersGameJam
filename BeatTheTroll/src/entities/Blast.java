// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import animation.BlastAnimation;
import animation.LimitAnimation;
import animation.TextAnimation;
import gameState.Level;
import math.Vec2;
import misc.GlobalController;
import physics.DynamicPhysicsData;
import physics.Manifold;
import tests.PlayerTest;

public class Blast extends Entity{
	private boolean reverse;
	
	public Blast(Level ref, Vec2 pos,boolean rev){
		super(ref);
		reverse = rev;
		anim = new BlastAnimation(this);
		ai = null;
		DynamicPhysicsData tmp;
		if(reverse){
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.getVel().x = -2.0f;
		}else{
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.getVel().x = 2.0f;
		}
		pd = tmp;
		harmfulToP = true;
	}
	
	public void update(){
		super.update();
		if(this.outOfBounds())
			getRef().addEntityToRemove(this);
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getOne() instanceof Player || fold.getOne() instanceof PlayerTest){
			getRef().addEntityToRemove(this);
			Player p = (Player)fold.getOne();
			if(!p.invulnerable){
				int val = GlobalController.level * 200;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				getRef().addExtraAnimation(new LimitAnimation(a,fold.one.pd.pos,30,true));
				p.invulnerable = true;
				p.invulnerableTimer = 20;
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
	
	// getter methods
	public boolean getReverse(){return reverse;}
}
