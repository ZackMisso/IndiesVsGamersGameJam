// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import java.awt.Graphics2D;

import gameState.Level;
import math.Vec2;

public class ExplosionAnimation extends Animation{
	private Level lev;
	private Vec2 pos;
	
	public ExplosionAnimation(Level param, Vec2 param2){
		super(null);
		lev = param;
		pos = param2;
		getImages().add(lev.getGSM().getParser().getImage("Explosion"));
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		if(getVisible())
			g.drawImage(getImages().get(getCurrentIndex()),(int)pos.x,(int)pos.y,null);
	}
}
