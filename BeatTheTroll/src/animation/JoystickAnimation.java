package animation;

import java.awt.Graphics2D;

import entities.Entity;
import math.Vec2;

public class JoystickAnimation extends Animation{
	public JoystickAnimation(Entity en){
		super(en);
		images = en.ref.gsm.parser.getImages("Joystick",3);
	}
	
	public void update(){
	}
	
	public void center(){
		currentIndex = 0;
	}
	
	public void right(){
		currentIndex = 2;
	}
	
	public void left(){
		currentIndex = 1;
	}
	
	public void draw(Graphics2D g, Vec2 pos){
		if(visible)
			g.drawImage(images.get(currentIndex), (int)pos.x, (int)pos.y, 140, 140, null);
	}
}
