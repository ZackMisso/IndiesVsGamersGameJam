// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import animation.ButtonAnimation;
import entities.UIButton;

public class ButtonAI implements ReactToInputAI{
	private UIButton button;
	
	public ButtonAI(UIButton param){
		button = param;
	}
	
	public void update(){}
	
	public void reactToKey(char key, boolean release){
		if(button.index == 0){
			// start
			if(key == '\n'){
				if(release){
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.release();
				}else{
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.press();
				}
			}
		}
		else if(button.index == 1){
			// select
			if(key == 's'){
				if(release){
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.release();
				}else{
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.press();
				}
			}
		}
		else if(button.index == 2){
			// a
			if(key == 'w'){
				if(release){
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.release();
				}else{
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.press();
				}
			}
		}
		else if(button.index == 3){
			// b
			if(key == ' '){
				if(release){
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.release();
				}else{
					ButtonAnimation an = (ButtonAnimation)button.anim;
					an.press();
				}
			}
		}
	}
}
