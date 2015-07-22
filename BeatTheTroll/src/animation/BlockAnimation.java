// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;
import art.ImageParser;

public class BlockAnimation extends Animation{
	public BlockAnimation(Entity en, int type){
		super(en);
		if(type == 1)
			images.add(en.getParser().getImage("Grass"));
		else if(type == 2)
			images.add(en.getParser().getImage("Block"));
	}
	
	public void update(){}
}
