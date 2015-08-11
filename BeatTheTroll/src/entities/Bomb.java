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
import physics.Manifold;
import tests.PlayerTest;

public class Bomb extends Entity{
	private int timeToExplode;
	private boolean onTimer;
	
	public Bomb(Level ref,Vec2 pos,Vec2 v){
		super(null);
		setRef(ref);
		setAnim(new BombAnimation(this));
		setAI(null);
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
				getRef().addEntityToRemove(this);
	}
	
	public void explode(){
		getPD().getPos().x -= 4;
		getRef().addExtraAnimation(new LimitAnimation(new ExplosionAnimation(getRef(),getPD().getPos()),getPD().getPos(),40,true));
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getOne() instanceof Player || fold.getOne() instanceof PlayerTest){
			explode();
			getRef().addEntityToRemove(this);
			Player p = (Player)fold.getOne();
			if(!p.getInvulnerable()){
				int val = 100 * getRef().getGSM().getStatus().getLevel();
				getRef().getGSM().getStatus().addToScore(-val);
				TextAnimation a = new TextAnimation("-"+val);
				getRef().addExtraAnimation(new LimitAnimation(a,fold.getOne().getPD().getPos(),30,true));
				p.nowInvulnerable(20);
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
	
	// setter methods
	public void setTimeToExplode(int param){timeToExplode=param;}
	public void setOnTimer(boolean param){onTimer=param;}
}
