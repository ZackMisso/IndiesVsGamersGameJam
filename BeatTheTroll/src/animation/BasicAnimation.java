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
		ArrayList<Image> ims = new ArrayList<>();
		ims.add(image);
		images = ims;
	}
	
	public void update(){}
	
	//public void draw(Graphics2D g, Vec2 pos){
	//	g.drawImage(images.get(0), (int)pos.x,(int)pos.y,null);
	//}
}
