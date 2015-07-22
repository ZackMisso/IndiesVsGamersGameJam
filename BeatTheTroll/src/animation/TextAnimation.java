// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;
import java.awt.Color;
import java.awt.Graphics2D;
import math.Vec2;

public class TextAnimation extends Animation{
	private String str;
	
	public TextAnimation(String param){
		super(null);
		str = param;
	}
	
	public void update(){}
	
	public void draw(Graphics2D g, Vec2 pos){
		g.setColor(Color.RED);
		g.drawString(str, (int)pos.x, (int)pos.y);
	}
}
