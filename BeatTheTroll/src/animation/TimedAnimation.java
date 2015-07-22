// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class TimedAnimation extends Animation{
	private int timer;
	
	public TimedAnimation(Entity en, int t){
		super(en);
		timer = t;
	}
	
	public void update(){
		timer--;
		if(timer<=0)
			makeInvisible();
	}
}
