// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import ai.ButtonAI;
import animation.ButtonAnimation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;
import physics.SimplePhysicsData;

public class UIButton extends Entity{
	private int index;
	
	public UIButton(Level lev,Vec2 pos, Vec2 siz, int in){
		super(null);
		ref = lev;
		anim = new ButtonAnimation(this);
		ai = new ButtonAI(this);
		pd = new SimplePhysicsData(pos,siz);
		index = in;
	}
	
	public void update(){}
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
