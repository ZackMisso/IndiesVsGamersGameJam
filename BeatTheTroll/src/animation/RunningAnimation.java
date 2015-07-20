// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;

public class RunningAnimation extends Animation{
	public int time;
	public int currentTime;
	public boolean stop;
	public boolean reverse;
	
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
				currentIndex++;
				if(currentIndex == images.size() && stop)
					ref.resetToDefaultAnimation();
				else{
					currentTime = 0;
					if(currentIndex == images.size())
						currentIndex = 0;
				}
			}
		}else{
			currentTime++;
			if(currentTime >= time){
				currentIndex--;
				if(currentIndex == 0 && stop)
					ref.resetToDefaultAnimation();
				else{
					currentTime = 0;
					currentIndex = images.size()-1;
				}
			}
		}
	}
	
	//public void draw(Graphics2D g, Vec2 pos){
	//	
	//}
}
