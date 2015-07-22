// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;
import math.Vec2;

public abstract class Animation {
	private Entity ref;
	private ArrayList<Image> images;
	private int currentIndex;
	private boolean visible;
	
	public Animation(Entity en){
		ref = en;
		images = new ArrayList<>();
		currentIndex = 0;
		visible = true;
	}
	
	public Animation(Entity en, ArrayList<Image> param){
		ref = en;
		images = param;
		currentIndex = 0;
		visible = true;
	}
	
	public abstract void update();
	
	public void draw(Graphics2D g, Vec2 pos){
		if(visible)
			g.drawImage(images.get(currentIndex),(int)pos.x,(int)pos.y,null);
	}
}
