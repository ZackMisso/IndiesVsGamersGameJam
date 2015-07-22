// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package tests;

import java.awt.Color;
import java.awt.Graphics2D;

import animation.Animation;
import entities.Entity;
import math.Vec2;

public class TestAnimation extends Animation{
	private Color color;
	
	public TestAnimation(Entity en, Color c){
		super(en);
		color = c;
	}
	
	public void update(){}
	
	public void draw(Graphics2D g,Vec2 pos){
		g.setColor(color);
		g.drawRect((int)pos.x, (int)pos.y, 32, 32);
	}
}
