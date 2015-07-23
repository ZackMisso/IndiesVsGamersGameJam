// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class ButtonAnimation extends Animation {	
	public ButtonAnimation(Entity en){
		super(en);
		setImages(en.getParser().getImages("Button",2));
	}
	
	public void update(){}
	
	public void press(){
		switchToIndex(2);
	}
	
	public void release(){
		switchToIndex(0);
	}
}
