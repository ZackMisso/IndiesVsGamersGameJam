// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class BlockAnimation extends Animation{
	public BlockAnimation(Entity en, int type){
		super(en);
		if(type == 1)
			images.add(en.ref.gsm.parser.getImage("Grass"));
		else if(type == 2)
			images.add(en.ref.gsm.parser.getImage("Block"));
	}
	
	public void update(){
		
	}
}
