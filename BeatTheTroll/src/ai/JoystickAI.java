// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import animation.JoystickAnimation;
import entities.Joystick;

public class JoystickAI implements ReactToInputAI{
	private Joystick stick;
	
	public JoystickAI(Joystick param){
		stick = param;
	}
	
	public void update(){}
	
	public void reactToKey(char key, boolean release){
		if(key == 'a' || key == 'd'){
			if(release){
				JoystickAnimation an = (JoystickAnimation)stick.getAnim();
				an.center();
			}
			else if(key == 'd'){
				JoystickAnimation an = (JoystickAnimation)stick.getAnim();
				an.right();
			}else{
				JoystickAnimation an = (JoystickAnimation)stick.getAnim();
				an.left();
			}
		}
	}
}
