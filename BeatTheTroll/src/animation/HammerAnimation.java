// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class HammerAnimation extends RunningAnimation{
	public HammerAnimation(Entity en, boolean rev){
		super(en);
		setTime(10);
		setCurrentTime(0);
		setStop(false);
		setImages(en.getParser().getImages("Hammer", 4));
		setReverse(rev);
		if(rev)
			setCurrentTime(3);
	}
}
