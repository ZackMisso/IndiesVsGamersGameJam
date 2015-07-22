// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import entities.Player;
import physics.DynamicPhysicsData;
import tests.PlayerTest;

public class PlayerAI implements ReactToInputAI{
	private Player player;
	private boolean right;
	private boolean up;
		
	public PlayerAI(Player p){
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
			}if(key == 'a' && !right){
				((DynamicPhysicsData)(player.pd)).acc.x = 0;
				((DynamicPhysicsData)(player.pd)).vel.x = 0;
			}
		}else{
			if(key == 'd' && !player.collidingRight){
				((DynamicPhysicsData)(player.pd)).acc.x = .5f;
				right = true;
				player.collidingLeft = false;
			}if(key == 's' && !player.collidingDown){
				up = false;
				player.collidingUp = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				((DynamicPhysicsData)(player.pd)).vel.y = ((DynamicPhysicsData)(player.pd)).maxVel.y;
			}if(key == 'a' && !player.collidingLeft){
				right = false;
				player.collidingRight = false;
				((DynamicPhysicsData)(player.pd)).acc.x = -.5f;
			}if((key == 'w' || key == ' ') && !player.collidingUp && !player.jumping){
				up = true;
				player.collidingDown = false;
				player.collidingRight = false;
				player.collidingLeft = false;
				player.jumping = true;
				((DynamicPhysicsData)(player.pd)).vel.y = -5.0f;
			}
		}
	}
}
