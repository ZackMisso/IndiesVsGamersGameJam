package animation;

import entities.Entity;

public class HammerAnimation extends RunningAnimation{
	public HammerAnimation(Entity en, boolean rev){
		super(en);
		time = 10;
		currentTime = 0;
		stop = false;
		images = en.ref.gsm.parser.getImages("Hammer", 4);
		reverse = rev;
		if(rev)
			currentTime = 3;
	}
}
