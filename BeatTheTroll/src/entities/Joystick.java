package entities;

import ai.JoystickAI;
import animation.JoystickAnimation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;
import physics.SimplePhysicsData;

public class Joystick extends Entity{
	public Joystick(Level lev,Vec2 pos, Vec2 siz){
		super(null);
		ref = lev;
		anim = new JoystickAnimation(this);
		pd = new SimplePhysicsData(pos, siz);
		ai = new JoystickAI(this);
	}
	
	public void handleManifold(Manifold fold){
		
	}
	
	public void resetToDefaultAnimation(){
		
	}
}
