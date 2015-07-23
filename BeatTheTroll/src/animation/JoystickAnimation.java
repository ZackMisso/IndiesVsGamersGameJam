// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Graphics2D;

import entities.Entity;
import math.Vec2;

public class JoystickAnimation extends Animation{
	public JoystickAnimation(Entity en){
		super(en);
		setImages(en.getParser().getImages("Joystick",3));
	}
	
	public void update(){}
	
	public void center(){
		switchToIndex(0);
	}
	
	public void right(){
		switchToIndex(2);
	}
	
	public void left(){
		switchToIndex(1);
	}
	
	public void draw(Graphics2D g, Vec2 pos){
		if(getVisible())
			g.drawImage(getImages().get(getCurrentIndex()), (int)pos.x, (int)pos.y, 140, 140, null);
	}
}
