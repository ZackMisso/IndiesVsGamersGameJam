// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import entities.Player;
import physics.DynamicPhysicsData;

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
				((DynamicPhysicsData)(player.getPD())).getAcc().x = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().x = 0;
			}if(key == 's' && !up){
				((DynamicPhysicsData)(player.getPD())).getAcc().y = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().y = 0;
			}if(key == 'a' && !right){
				((DynamicPhysicsData)(player.getPD())).getAcc().x = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().x = 0;
			}if(key == 'w' || key == ' ' && up){
				((DynamicPhysicsData)(player.getPD())).getAcc().y = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().y = 0;
			}
		}else{
			if(key == 'd' && !player.getCollidingRight()){
				((DynamicPhysicsData)(player.getPD())).getVel().x = 1.8f;
				right = true;
				player.setCollidingLeft(false);
			}if(key == 's' && !player.getCollidingDown()){
				up = false;
				player.setCollidingUp(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				((DynamicPhysicsData)(player.getPD())).getVel().y = 3.2f;
			}if(key == 'a' && !player.getCollidingLeft()){
				right = false;
				player.setCollidingRight(false);
				((DynamicPhysicsData)(player.getPD())).getVel().x = -1.8f;
			}if(key == 'w' || key == ' ' && !player.getCollidingUp()){
				up = true;
				player.setCollidingDown(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				((DynamicPhysicsData)(player.getPD())).getVel().y = -1.0f;
			}
		}
	}
}
