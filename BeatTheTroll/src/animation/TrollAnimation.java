// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;

public class TrollAnimation extends Animation{
	public TrollAnimation(Entity en){
		super(en);
		getImages().add(getImage("TrollOne"));
	}
	
	public void update(){
		
	}
	
	public void switchToFace1(){
		getImages().clear();
		getImages().add(getImage("TrollOne"));
	}
	
	public void switchToFace2(){
		getImages().clear();
		getImages().add(getImage("TrollTwo"));
	}
	
	public void switchToFace3(){
		getImages().clear();
		getImages().add(getImage("TrollFour"));
	}
	
	public void switchToFace4(){
		getImages().clear();
		getImages().add(getImage("TrollThree"));
	}
}
