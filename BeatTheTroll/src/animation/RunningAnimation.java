// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;

public class RunningAnimation extends Animation{
	private int time;
	private int currentTime;
	private boolean stop;
	private boolean reverse;
	
	public RunningAnimation(Entity en){
		super(en);
		currentTime = 0;
		stop = false;
		reverse = false;
	}
	
	public RunningAnimation(Entity en,ArrayList<Image> param){
		super(en,param);
		currentTime = 0;
		stop = false;
		reverse = false;
	}
	
	public void update(){
		if(!reverse){
			currentTime++;
			if(currentTime >= time){
				incrementCurrentIndex();
				if(getCurrentIndex() == getImages().size() && stop)
					getRef().resetToDefaultAnimation();
				else{
					currentTime = 0;
					if(getCurrentIndex() == getImages().size())
						switchToIndex(0);
				}
			}
		}else{
			currentTime++;
			if(currentTime >= time){
				decrementCurrentIndex();
				if(getCurrentIndex() == 0 && stop)
					getRef().resetToDefaultAnimation();
				else{
					currentTime = 0;
					switchToIndex(getImages().size()-1);
				}
			}
		}
	}
	
	// getter methods
	public int getTime(){return time;}
	public int getCurrentTime(){return currentTime;}
	public boolean getStop(){return stop;}
	public boolean getReverse(){return reverse;}
	
	// setter methods
	public void setTime(int param){time=param;}
	public void setCurrentTime(int param){currentTime=param;}
	public void setStop(boolean param){stop=param;}
	public void setReverse(boolean param){reverse=param;}
}
