package entities;

import ai.ButtonAI;
import animation.ButtonAnimation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;
import physics.SimplePhysicsData;

public class UIButton extends Entity{
	public int index;
	
	public UIButton(Level lev,Vec2 pos, Vec2 siz, int in){
		super(null);
		ref = lev;
		anim = new ButtonAnimation(this);
		ai = new ButtonAI(this);
		pd = new SimplePhysicsData(pos,siz);
		index = in;
	}
	
	public void update(){
		
	}
	
	public void reactToMouse(int x, int y){
		
	}
	
	public void reactToKey(char key, boolean release){
		
	}
	
	public void handleManifold(Manifold fold){
		
	}
	
	public void resetToDefaultAnimation(){
		
	}
}
