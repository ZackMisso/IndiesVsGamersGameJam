package animation;

import entities.Entity;

public class BombAnimation extends Animation{
	public BombAnimation(Entity en){
		super(en);
		images.add(en.ref.gsm.parser.getImage("Bomb"));
	}
	
	public void update(){
		
	}
}
