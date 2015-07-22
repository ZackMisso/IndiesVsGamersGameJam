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
		ai = new TrollAI(this);
		pd = new DynamicPhysicsData(new Vec2(400,400),new Vec2(100,100));
		anim = new TrollAnimation(this);
		level = 1;
		first = true;
	}
	
	public void reset(){
		if(first){
			((TrollAI)ai).reset();
			pd.pos = new Vec2(60,500);
			((TrollAI)ai).active = true;
			((TrollAI)ai).moveTo = new Vec2(600,500);
			((TrollAI)ai).moveToSpace = true;
		}else{
			((TrollAI)ai).reset();
			pd.pos = new Vec2(60,200);
			((TrollAI)ai).active = true;
			((TrollAI)ai).moveTo = new Vec2(500,200);
			((TrollAI)ai).moveToSpace = true;
		}
	}
	
	public void changeToOne(){
		level = 1;
		TrollAnimation an = (TrollAnimation)anim;
		an.switchToFace1();
	}
	
	public void changeToTwo(){
		level = 2;
		TrollAnimation an = (TrollAnimation)anim;
		an.switchToFace2();
	}
	
	public void changeToThree(){
		level = 3;
		TrollAnimation an = (TrollAnimation)anim;
		an.switchToFace3();
	}
	
	public void changeToFour(){
		level = 4;
		TrollAnimation an = (TrollAnimation)anim;
		an.switchToFace4();
	}
	
	public void handleManifold(Manifold fold){
		if(fold.one instanceof Player){
			Player p = (Player)fold.one;
			if(!p.invulnerable){
				int val = 500 * GlobalController.level;
				GlobalController.score -= val;
				TextAnimation a = new TextAnimation("-"+val);
				ref.extraAnimations.add(new LimitAnimation(a,fold.one.pd.pos,30,true));
				p.invulnerable = true;
				p.invulnerableTimer = 20;
			}
		}
	}
	
	public void resetToDefaultAnimation(){}
	
	// getter methods
	public int getLevel(){return level;}
}
