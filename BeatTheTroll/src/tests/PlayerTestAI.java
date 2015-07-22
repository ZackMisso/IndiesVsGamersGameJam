// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package tests;

import ai.ReactToInputAI;
import physics.DynamicPhysicsData;

public class PlayerTestAI implements ReactToInputAI{
	private PlayerTest player;
	private boolean right;
	private boolean up;
	
	public PlayerTestAI(PlayerTest p){
		player = p;
		up = false;
		right = false;
	}
	
	public void update(){}
	
	public void reactToKey(char key, boolean release){
		if(release){
			if(key == 'd' && right){
				((DynamicPhysicsData)(player.pd)).acc.x = 0;
				((DynamicPhysicsData)(player.pd)).vel.x = 0;
			}if(key == 's' && !up){
				//((DynamicPhysicsData)(player.pd)).acc.y = 0;
				//((DynamicPhysicsData)(player.pd)).vel.y = 0;
			}if(key == 'a' && !right){
				((DynamicPhysicsData)(player.pd)).acc.x = 0;
				((DynamicPhysicsData)(player.pd)).vel.x = 0;
			}if(key == 'w' && up){
				//((DynamicPhysicsData)(player.pd)).acc.y = 0;
				//((DynamicPhysicsData)(player.pd)).vel.y = 0;
			}
		}else{
			if(key == 'd' && !player.getCollidingRight()){
				((DynamicPhysicsData)(player.pd)).acc.x = .2f;
				//((DynamicPhysicsData)(player.pd)).vel.x = 0f;
				//System.out.println("WHAT");
				right = true;
				player.setCollidingLeft(false);
			}if(key == 's' && !player.getCollidingDown()){
				up = false;
				player.setCollidingUp(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				//((DynamicPhysicsData)(player.pd)).acc.y = .5f;
				((DynamicPhysicsData)(player.pd)).vel.y = ((DynamicPhysicsData)(player.pd)).maxVel.y;
			}if(key == 'a' && !player.getCollidingLeft()){
				right = false;
				player.setCollidingRight(false);
				((DynamicPhysicsData)(player.pd)).acc.x = -.2f;
				//((DynamicPhysicsData)(player.pd)).vel.x = 0f; // -
			}if(key == 'w' && !player.getCollidingUp() && !player.getJumping()){
				up = true;
				player.setCollidingDown(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				player.jumping = true;
				//((DynamicPhysicsData)(player.pd)).acc.y = -.5f;
				((DynamicPhysicsData)(player.pd)).vel.y = -5.0f; // -
			}
		}
	}
}
