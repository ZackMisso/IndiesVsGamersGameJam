package animation;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;
import entities.ItemUI;
import math.Vec2;

public class ItemUIAnimation extends Animation{
	//public Color color;
	public ItemUI item;
	public int timer;
	
	public ItemUIAnimation(Entity en){
		super(en);
		//color = new Color.BLACK;
		timer = 0;
		item = (ItemUI)en;
		images = en.ref.gsm.parser.getImages("button",2);
		//item.ready = false;
	}
	
	public void update(){
		if(!item.ready){
			timer++;
			if(timer >= item.reloadTime){
				timer = 0;
				item.ready = true;
			}
		}
	}
	
	public void reset(){
		timer = 0;
	}
	
	public void draw(Graphics2D g, Vec2 pos){
		super.draw(g,pos);
		if(visible){
			if(!item.found){
				g.setColor(Color.BLACK);
				g.fillRect((int)pos.x,(int)pos.y,40,40);
			}
			else if(!item.ready){
				g.setColor(Color.BLACK);
				g.fillRect((int)pos.x, (int)pos.y, (int)(40 * ((float)timer/(float)item.reloadTime)), (int)(40 * ((float)timer/(float)item.reloadTime)));
			}
		}
	}
}
