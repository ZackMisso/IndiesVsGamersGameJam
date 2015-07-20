package entities;

import animation.BlastAnimation;
import animation.LimitAnimation;
import animation.TextAnimation;
import gameState.Level;
import math.Vec2;
import misc.GlobalController;
import physics.Collidable;
import physics.DynamicPhysicsData;
import physics.Manifold;
import tests.PlayerTest;

public class Blast extends Entity{
	public boolean reverse;
	
	public Blast(Level ref, Vec2 pos,boolean rev){
		super(ref);
		reverse = rev;
		anim = new BlastAnimation(this);
		ai = null;
		DynamicPhysicsData tmp;
		if(reverse){
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.vel.x = -2.0f;
		}else{
			tmp = new DynamicPhysicsData(pos, new Vec2(20,20));
			tmp.vel.x = 2.0f;
		}
		pd = tmp;
		harmfulToP = true;
	}
	
	public void update(){
		super.update();
		if(this.outOfBounds()){
			ref.entitiesToRemove.add(this);
			//System.out.println("OUT OF BOUNDS");
		}
	}
	
	public void handleManifold(Manifold fold){
		if(fold.one instanceof Player || fold.one instanceof PlayerTest){
			ref.entitiesToRemove.add(this);
			Player p = (Player)fold.one;
			if(!p.invulnerable){
				int val = GlobalController.level * 200;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				ref.extraAnimations.add(new LimitAnimation(a,fold.one.pd.pos,30,true));
				
				p.invulnerable = true;
				p.invulnerableTimer = 20;
			}
		}
	}
	
	public void resetToDefaultAnimation(){
		
	}
}