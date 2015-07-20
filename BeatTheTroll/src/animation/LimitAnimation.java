package animation;

import java.awt.Graphics2D;

import math.Vec2;

public class LimitAnimation{
	public Animation anim;
	public Animation def;
	public Vec2 pos;
	public Vec2 vel;
	public int timer;
	public boolean removeOnTimer;
	public boolean onDefault;
	
	public LimitAnimation(Animation param, Vec2 vec, int param2, boolean param3){
		anim = param;
		pos = vec;
		timer = param2;
		removeOnTimer = param3;
		onDefault = false;
		vel = new Vec2();
	}
	
	public void update(){
		anim.update();
		pos.add(vel);
	}
	
	public boolean checkRemove(){
		timer--;
		if(timer <= 0){
			if(removeOnTimer)
				return true;
			else{
				onDefault = true;
			}
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		if(onDefault)
			def.draw(g, pos);
		else
			anim.draw(g, pos);
	}
}
