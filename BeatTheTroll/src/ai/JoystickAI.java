package ai;

import animation.JoystickAnimation;
import entities.Joystick;

public class JoystickAI implements ReactToInputAI{
	public Joystick stick;
	
	public JoystickAI(Joystick param){
		stick = param;
	}
	
	public void update(){
		
	}
	
	public void reactToMouse(int x, int y){
		//JoystickAnimation an = (JoystickAnimation)stick.anim;
		//an.
	}
	
	public void reactToKey(char key, boolean release){
		if(key == 'a' || key == 'd'){
			if(release){
				JoystickAnimation an = (JoystickAnimation)stick.anim;
				an.center();
			}
			else if(key == 'd'){
				JoystickAnimation an = (JoystickAnimation)stick.anim;
				an.right();
			}else{
				JoystickAnimation an = (JoystickAnimation)stick.anim;
				an.left();
			}
		}
	}
}
