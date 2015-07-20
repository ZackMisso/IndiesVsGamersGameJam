// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class ButtonAnimation extends Animation {	
	public ButtonAnimation(Entity en){
		super(en);
		images = en.ref.gsm.parser.getImages("Button",2);
	}
	
	public void update(){}
	
	public void press(){
		currentIndex = 1;
	}
	
	public void release(){
		currentIndex = 0;
	}
}
