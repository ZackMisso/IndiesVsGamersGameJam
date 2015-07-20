package audio;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Song implements LineListener{
	public boolean finished;
	
	public Song(){
		finished = false;
	}
	
	public void update(LineEvent event){
		if(event.getType() == LineEvent.Type.STOP)
			finished = true;
	}
}
