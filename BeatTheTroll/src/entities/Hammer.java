// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import animation.HammerAnimation;
import animation.LimitAnimation;
import animation.TextAnimation;
import gameState.Level;
import math.Vec2;
import misc.GlobalController;
import misc.RNG;
import physics.DynamicPhysicsData;
import physics.Manifold;
import tests.PlayerTest;

public class Hammer extends Entity{
	public Hammer(Level lev, Vec2 pos){
		super(lev);
		setAI(null);
		DynamicPhysicsData tmp = new DynamicPhysicsData(pos, new Vec2(40,40));
		tmp.getAcc().y = .3f;
		tmp.getVel().y = -2f * RNG.getNextFloat();
		tmp.getVel().x = RNG.getNextFloat();
		boolean rev = false;
		if(RNG.getNextBool()){
			tmp.getVel().x *= -1;
			rev = true;
		}
		setPD(tmp);
		setAnim(new HammerAnimation(this,rev));
		setHarmfulToP(true);
	}
	
	public Hammer(Level lev, Vec2 pos,boolean rev){
		super(lev);
		setAI(null);
		DynamicPhysicsData tmp = new DynamicPhysicsData(pos, new Vec2(40,40));
		tmp.getAcc().y = .03f;
		tmp.getVel().y = -2f * RNG.getNextFloat();
		tmp.getVel().x = RNG.getNextFloat();
		if(RNG.getNextBool())
			tmp.getVel().x *= -1;
		setPD(tmp);
		setAnim(new HammerAnimation(this,rev));
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
				int val = 50 * GlobalController.level;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation((-val)+"");
				getRef().addExtraAnimation(new LimitAnimation(a,fold.getOne().getPD().getPos(),30,true));
				p.nowInvulnerable(20);
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
}
