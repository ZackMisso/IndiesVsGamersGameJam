package animation;
import java.awt.Color;
import java.awt.Graphics2D;
import math.Vec2;

public class TextAnimation extends Animation{
	public String str;
	
	public TextAnimation(String param){
		super(null);
		str = param;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, Vec2 pos){
		g.setColor(Color.RED);
		g.drawString(str, (int)pos.x, (int)pos.y);
	}
}
