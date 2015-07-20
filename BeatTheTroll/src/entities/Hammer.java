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
		ai = null;
		DynamicPhysicsData tmp = new DynamicPhysicsData(pos, new Vec2(40,40));
		tmp.acc.y = .3f;
		tmp.vel.y = -2f * RNG.getNextFloat();
		tmp.vel.x = RNG.getNextFloat();
		boolean rev = false;
		if(RNG.getNextBool()){
			tmp.vel.x *= -1;
			rev = true;
		}
		pd = tmp;
		anim = new HammerAnimation(this,rev);
		harmfulToP = true;
	}
	
	public Hammer(Level lev, Vec2 pos,boolean rev){
		super(lev);
		ai = null;
		DynamicPhysicsData tmp = new DynamicPhysicsData(pos, new Vec2(40,40));
		tmp.acc.y = .03f;
		tmp.vel.y = -2f * RNG.getNextFloat();
		tmp.vel.x = RNG.getNextFloat();
		if(RNG.getNextBool())
			tmp.vel.x *= -1;
		pd = tmp;
		anim = new HammerAnimation(this,rev);
		harmfulToP = true;
	}
	
	public void update(){
		super.update();
		if(this.outOfBounds())
			ref.entitiesToRemove.add(this);
	}
	
	public void handleManifold(Manifold fold){
		if(fold.one instanceof Player || fold.one instanceof PlayerTest){
			ref.entitiesToRemove.add(this);
			Player p = (Player)fold.one;
			if(!p.invulnerable){
				int val = 50 * GlobalController.level;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation((-val)+"");
				ref.extraAnimations.add(new LimitAnimation(a,fold.one.pd.pos,30,true));
				
				p.invulnerable = true;
				p.invulnerableTimer = 20;
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
}
