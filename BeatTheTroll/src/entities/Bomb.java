package entities;

import animation.BombAnimation;
import animation.ExplosionAnimation;
import animation.LimitAnimation;
import animation.TextAnimation;
import gameState.Level;
import math.Vec2;
import misc.GlobalController;
import physics.DynamicPhysicsData;
import physics.Manifold;
import tests.PlayerTest;

public class Bomb extends Entity{
	public int timeToExplode;
	public boolean onTimer;
	
	public Bomb(Level ref,Vec2 pos,Vec2 v){
		super(ref);
		anim = new BombAnimation(this);
		pd = new DynamicPhysicsData(pos,new Vec2(36,50));
		DynamicPhysicsData tmp = (DynamicPhysicsData)pd;
		tmp.vel = v;
		ai = null;
		onTimer = false;
	}
	
	public void update(){
		super.update();
		if(onTimer){
			timeToExplode--;
			if(timeToExplode <= 0){
				explode();
				ref.entitiesToRemove.add(this);
			}
		}else{
			if(this.outOfBounds()){
				ref.entitiesToRemove.add(this);
				//System.out.println("OUT OF BOUNDS");
			}
		}
	}
	
	public void explode(){
		//ref.extraAnimations.add(new ExplosionAnimation());
		//if(ref == null)
		//	System.out.println("Ref is null\n");
		//if(pd == null)
		//	System.out.println("PD is null");
		//if(pd.pos == null)
		//	System.out.println("Pos is null\n");
		pd.pos.x -= 4;
		ref.extraAnimations.add(new LimitAnimation(new ExplosionAnimation(ref,pd.pos),pd.pos,40,true));
		
	}
	
	public void handleManifold(Manifold fold){
		if(fold.one instanceof Player || fold.one instanceof PlayerTest){
			explode();
			ref.entitiesToRemove.add(this);
			Player p = (Player)fold.one;
			if(!p.invulnerable){
				int val = 100 * GlobalController.level;
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
