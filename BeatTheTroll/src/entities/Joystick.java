// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;

import ai.JoystickAI;
import animation.JoystickAnimation;
import gameState.Level;
import math.Vec2;
import physics.Manifold;
import physics.SimplePhysicsData;

public class Joystick extends Entity{
	public Joystick(Level lev,Vec2 pos, Vec2 siz){
		super(lev,new JoystickAnimation(this),new SimplePhysicsData(pos,siz),new JoystickAI(this));
	}
	
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
