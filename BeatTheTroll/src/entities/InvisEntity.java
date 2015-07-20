// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package entities;
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
	
	public void update(){	}
	public void draw(Graphics2D g){}
	public void handleManifold(Manifold fold){}
	public void resetToDefaultAnimation(){}
}
