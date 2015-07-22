// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class BombAnimation extends Animation{
	public BombAnimation(Entity en){
		super(en);
		images.add(en.getParser().getImage("Bomb"));
	}
	
	public void update(){}
}
