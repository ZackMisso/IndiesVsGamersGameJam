package entities;

import ai.AI;
import animation.Animation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;

public class Item extends Entity{
	public Item(Level l, Animation a, Vec2 pos, Vec2 siz, AI i){
		super(l,a,pos,siz,i);
	}
	
	public void handleManifold(Manifold fold){
		
	}
	
	public void resetToDefaultAnimation(){
		
	}
}
