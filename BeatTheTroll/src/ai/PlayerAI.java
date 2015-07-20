package ai;

import entities.Player;
import physics.DynamicPhysicsData;
import tests.PlayerTest;

public class PlayerAI implements ReactToInputAI{
	public Player player;
	boolean right;
	boolean up;
		
	public PlayerAI(Player p){
		player = p;
		up = false;
		right = false;
	}
		
	public void update(){
		
	}
		
	public void reactToMouse(int x, int y){
		
	}
		
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
			}if((key == 'w' || key == ' ') && up){
				//((DynamicPhysicsData)(player.pd)).acc.y = 0;
				//((DynamicPhysicsData)(player.pd)).vel.y = 0;
			}
		}else{
			if(key == 'd' && !player.collidingRight){
				((DynamicPhysicsData)(player.pd)).acc.x = .5f;
				//((DynamicPhysicsData)(player.pd)).vel.x = 0f;
				//System.out.println("WHAT");
				right = true;
				player.collidingLeft = false;
			}if(key == 's' && !player.collidingDown){
				up = false;
				player.collidingUp = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				//((DynamicPhysicsData)(player.pd)).acc.y = .5f;
				((DynamicPhysicsData)(player.pd)).vel.y = ((DynamicPhysicsData)(player.pd)).maxVel.y;
			}if(key == 'a' && !player.collidingLeft){
				right = false;
				player.collidingRight = false;
				((DynamicPhysicsData)(player.pd)).acc.x = -.5f;
				//((DynamicPhysicsData)(player.pd)).vel.x = 0f; // -
			}if((key == 'w' || key == ' ') && !player.collidingUp && !player.jumping){
				up = true;
				player.collidingDown = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				player.jumping = true;
				//((DynamicPhysicsData)(player.pd)).acc.y = -.5f;
				((DynamicPhysicsData)(player.pd)).vel.y = -5.0f; // -
			}
		}
	}
}
