// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;
import entities.Troll;

public class TrollAnimation extends Animation{
	public Troll troll;
	public int face;
	
	public TrollAnimation(Entity en){
		super(en);
		troll = (Troll)en;
		images.add(troll.gsmRef.parser.getImage("TrollOne"));
		face = 1;
	}
	
	public void update(){
		
	}
	
	public void switchToFace1(){
		images.clear();
		face = 1;
		images.add(troll.ref.gsm.parser.getImage("TrollOne"));
	}
	
	public void switchToFace2(){
		images.clear();
		face = 2;
		images.add(troll.ref.gsm.parser.getImage("TrollTwo"));
	}
	
	public void switchToFace3(){
		images.clear();
		face = 3;
		images.add(troll.ref.gsm.parser.getImage("TrollFour"));
	}
	
	public void switchToFace4(){
		images.clear();
		face = 4;
		images.add(troll.ref.gsm.parser.getImage("TrollThree"));
	}
}
