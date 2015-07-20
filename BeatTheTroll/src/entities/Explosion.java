// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import ai.AI;
import animation.Animation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;

public class Explosion extends Entity{
	public boolean harmful;
	public long timer;
	
	public Explosion(Level l, Animation a, Vec2 pos, Vec2 siz, AI i){
		super(l,a,pos,siz,i);
		harmful = true;
		timer = 60 * 3; // 3 seconds
	}
	
	public void update(){
		anim.update();
		timer--;
		if(timer==0)
			harmful=false;
	}
	
	public void handleManifold(Manifold fold){}
	
	public void resetToDefaultAnimation(){
		alive = false;
	}
}
