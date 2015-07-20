package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import gameState.Level;
import math.Vec2;
import physics.Collidable;
import physics.Manifold;
import physics.SimplePhysicsData;

public class InvisEntity extends Entity implements Collidable{
	public InvisEntity(Level lev,Vec2 pos,Vec2 siz){
		super(lev);
		anim = null;
		ai = null;
		pd = new SimplePhysicsData(pos,siz);
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		//g.setColor(Color.BLACK);
		//g.drawRect((int)pd.pos.x,(int)pd.pos.y,(int)pd.siz.x,(int)pd.siz.y);
	}
	
	public void handleManifold(Manifold fold){
		
	}
	
	public void resetToDefaultAnimation(){
		
	}
}
