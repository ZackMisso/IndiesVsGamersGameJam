// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import entities.Player;
import physics.DynamicPhysicsData;
import tests.PlayerTest;

public class PlayerFallingAI implements ReactToInputAI{
	private Player player;
	private boolean right;
	private boolean up;
		
	public PlayerFallingAI(Player p){
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
				((DynamicPhysicsData)(player.pd)).acc.y = 0;
				((DynamicPhysicsData)(player.pd)).vel.y = 0;
			}if(key == 'a' && !right){
				((DynamicPhysicsData)(player.pd)).acc.x = 0;
				((DynamicPhysicsData)(player.pd)).vel.x = 0;
			}if(key == 'w' || key == ' ' && up){
				((DynamicPhysicsData)(player.pd)).acc.y = 0;
				((DynamicPhysicsData)(player.pd)).vel.y = 0;
			}
		}else{
			if(key == 'd' && !player.collidingRight){
				((DynamicPhysicsData)(player.pd)).vel.x = 1.8f;
				right = true;
				player.collidingLeft = false;
			}if(key == 's' && !player.collidingDown){
				up = false;
				player.collidingUp = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				((DynamicPhysicsData)(player.pd)).vel.y = 3.2f;
			}if(key == 'a' && !player.collidingLeft){
				right = false;
				player.collidingRight = false;
				((DynamicPhysicsData)(player.pd)).vel.x = -1.8f;
			}if(key == 'w' || key == ' ' && !player.collidingUp){
				up = true;
				player.collidingDown = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				((DynamicPhysicsData)(player.pd)).vel.y = -1.0f;
			}
		}
	}
}
