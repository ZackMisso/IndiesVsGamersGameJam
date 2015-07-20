package entities;

import ai.ItemUIAI;
import animation.ItemUIAnimation;
import math.Vec2;
import physics.Manifold;
import physics.SimplePhysicsData;

public class ItemUI extends Entity{
	public int reloadTime;
	public boolean ready;
	public boolean found;
	
	public ItemUI(Vec2 pos, Vec2 siz){
		super(null);
		anim = new ItemUIAnimation(this);
		pd = new SimplePhysicsData(pos,siz);
		ai = new ItemUIAI(this);
		reloadTime = 3000;
		ready = false;
		found = false;
	}
	
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
