// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package tests;

import java.awt.Color;

import entities.Entity;
import math.Vec2;
import physics.Collidable;
import physics.Manifold;
import physics.SimplePhysicsData;

public class BlockTest extends Entity implements Collidable{
	public BlockTest(Vec2 pos){
		super(null);
		setAnim(new TestAnimation(this, Color.RED));
		setAI(null);
		setPD(new SimplePhysicsData(pos, new Vec2(32,32)));
	}
	
	public BlockTest(){
		super(null);
		setAnim(new TestAnimation(this, Color.RED));
		setAI(null);
		setPD(new SimplePhysicsData(new Vec2(300,500),new Vec2(32,32)));
	}
	
	//public void update(){
	//	
	//}
	
	public void handleManifold(Manifold fold){}
	
	public void resetToDefaultAnimation(){
		setAnim(new TestAnimation(this, Color.RED));
	}
}
