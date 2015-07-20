package animation;

import java.awt.Graphics2D;

import gameState.Level;
import math.Vec2;

public class ExplosionAnimation extends Animation{
	public Level lev;
	public Vec2 pos;
	
	public ExplosionAnimation(Level param, Vec2 param2){
		super(null);
		//images.add(lev.gsm.parser.getImage("Explosion"));
		lev = param;
		pos = param2;
		images.add(lev.gsm.parser.getImage("Explosion"));
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		if(visible)
			g.drawImage(images.get(currentIndex),(int)pos.x,(int)pos.y,null);
	}
}