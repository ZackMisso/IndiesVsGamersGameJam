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
		setAnim(new BlastAnimation(this));
		setAI(null);
		DynamicPhysicsData tmp;
		if(reverse){
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.getVel().x = -2.0f;
		}else{
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.getVel().x = 2.0f;
		}
		setPD(tmp);
		setHarmfulToP(true);
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
			if(!p.getInvulnerable()){
				int val = GlobalController.level * 200;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				getRef().addExtraAnimation(new LimitAnimation(a,fold.getOne().getPD().getPos(),30,true));
				p.nowInvulnerable(20);
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
	
	// getter methods
	public boolean getReverse(){return reverse;}
}
