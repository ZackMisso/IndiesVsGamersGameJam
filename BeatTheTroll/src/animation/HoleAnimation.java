// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class HoleAnimation extends Animation{
	public HoleAnimation(Entity en, boolean sideways){
		super(en);
		if(sideways)
			images.add(en.ref.gsm.parser.getImage("SideHole"));
		else
			images.add(en.ref.gsm.parser.getImage("Hole"));
	}
	
	public void update(){}
}
