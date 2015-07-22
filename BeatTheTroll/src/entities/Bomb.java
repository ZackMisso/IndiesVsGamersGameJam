// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

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
	private int timeToExplode;
	private boolean onTimer;
	
	public Bomb(Level ref,Vec2 pos,Vec2 v){
		super(ref,new BombAnimation(this),null,null);
		createDynamicPhysics(pos,new Vec2(36,50),v,new Vec2());
		onTimer = false;
	}
	
	public void update(){
		super.update();
		if(onTimer){
			timeToExplode--;
			if(timeToExplode <= 0){
				explode();
				getRef().addEntityToRemove(this);
			}
		}else
			if(this.outOfBounds())
				getRef().addEntityToRemove.add(this);
	}
	
	public void explode(){
		pd.pos.x -= 4;
		pd.translate(-4,0);
		getRef().addExtraAnimation(new LimitAnimation(new ExplosionAnimation(getRef(),pd.pos),pd.pos,40,true));
	}
	
	public void handleManifold(Manifold fold){
		if(fold.one instanceof Player || fold.one instanceof PlayerTest){
			explode();
			getRef().addEntityToRemove(this);
			Player p = (Player)fold.getOne();
			if(!p.invulnerable){
				int val = 100 * GlobalController.level;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				getRef().addExtraAnimation(new LimitAnimation(a,fold.one.pd.pos,30,true));
				p.invulnerable = true;
				p.invulnerableTimer = 20;
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
}
