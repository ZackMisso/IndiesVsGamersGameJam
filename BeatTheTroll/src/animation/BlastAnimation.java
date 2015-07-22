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
			getImages().add(en.getRef().gsm.parser.getImage("BlastReverse"));
		else
			images.add(en.ref.gsm.parser.getImage("Blast"));
	}
	
	public void update(){}
}
