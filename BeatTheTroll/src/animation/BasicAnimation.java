// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;

public class BasicAnimation extends Animation{
	public BasicAnimation(Entity en, Image image){
		super(en);
		getImages().add(image);
	}
	
	public void update(){}
}
