// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class HoleAnimation extends Animation{
	public HoleAnimation(Entity en, boolean sideways){
		super(en);
		if(sideways)
			getImages().add(en.getParser().getImage("SideHole"));
		else
			getImages().add(en.getParser().getImage("Hole"));
	}
	
	public void update(){}
}
