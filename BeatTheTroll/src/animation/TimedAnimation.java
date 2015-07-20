package animation;

import entities.Entity;

public class TimedAnimation extends Animation{
	public int timer;
	
	public TimedAnimation(Entity en, int t){
		super(en);
		timer = t;
	}
	
	public void update(){
		timer--;
		if(timer<=0){
			visible = false;
		}
	}
}