// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import ai.TrollAI;
import animation.LimitAnimation;
import animation.TextAnimation;
import animation.TrollAnimation;
import gameState.GameStateManager;
import math.Vec2;
import misc.GlobalController;
import physics.DynamicPhysicsData;
import physics.Manifold;

public class Troll extends Entity{
	private GameStateManager gsmRef;
	private int level;
	private boolean first;
	
	public Troll(GameStateManager ref){
		super(null);
		gsmRef = ref;
		setAI(new TrollAI(this));
		setPD(new DynamicPhysicsData(new Vec2(400,400),new Vec2(100,100)));
		setAnim(new TrollAnimation(this));
		level = 1;
		first = true;
	}
	
	public void reset(){
		if(first){
			((TrollAI)getAI()).reset();
			getPD().setPos(new Vec2(60,500));
			((TrollAI)getAI()).setActive(true);
			((TrollAI)getAI()).setMoveTo(new Vec2(600,500));
			((TrollAI)getAI()).setMoveToSpace(true);
		}else{
			((TrollAI)getAI()).reset();
			getPD().setPos(new Vec2(60,200));
			((TrollAI)getAI()).setActive(true);
			((TrollAI)getAI()).setMoveTo(new Vec2(500,200));
			((TrollAI)getAI()).setMoveToSpace(true);
		}
	}
	
	public void changeToOne(){
		level = 1;
		TrollAnimation an = (TrollAnimation)getAnim();
		an.switchToFace1();
	}
	
	public void changeToTwo(){
		level = 2;
		TrollAnimation an = (TrollAnimation)getAnim();
		an.switchToFace2();
	}
	
	public void changeToThree(){
		level = 3;
		TrollAnimation an = (TrollAnimation)getAnim();
		an.switchToFace3();
	}
	
	public void changeToFour(){
		level = 4;
		TrollAnimation an = (TrollAnimation)getAnim();
		an.switchToFace4();
	}
	
	public void handleManifold(Manifold fold){
		if(fold.getOne() instanceof Player){
			Player p = (Player)fold.getOne();
			if(!p.getInvulnerable()){
				int val = 500 * GlobalController.level;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				getRef().addExtraAnimation(new LimitAnimation(a,fold.getOne().getPD().getPos(),30,true));
				p.nowInvulnerable(20);
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
	
	// getter methods
	public int getLevel(){return level;}
}
