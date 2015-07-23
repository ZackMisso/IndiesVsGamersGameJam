// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Blast;
import entities.Entity;

public class BlastAnimation extends Animation{
	public BlastAnimation(Entity en){
		super(en);
		if(((Blast)getRef()).getReverse())
			getImages().add(en.getParser().getImage("BlastReverse"));
		else
			getImages().add(en.getParser().getImage("Blast"));
	}
	
	public void update(){}
}
