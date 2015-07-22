// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;

public class ReverseRunningAnimation extends Animation{
	private int time;
	private int currentTime;
	private boolean stop;
	
	public ReverseRunningAnimation(Entity en){
		super(en);
	}
	
	public ReverseRunningAnimation(Entity en,ArrayList<Image> param){
		super(en,param);
	}
	
	public void update(){
		currentTime++;
		if(currentTime == time){
			currentIndex++;
			if(currentIndex == images.size() && stop)
				ref.resetToDefaultAnimation();
			else{
				currentTime = 0;
				currentIndex = 0;
			}
		}
	}
	
	//public void draw(Graphics2D g, Vec2 pos){
	//	
	//}
}
