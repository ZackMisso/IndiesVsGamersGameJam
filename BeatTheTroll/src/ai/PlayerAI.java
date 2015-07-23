// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import entities.Player;
import physics.DynamicPhysicsData;

public class PlayerAI implements ReactToInputAI{
	private Player player;
	private boolean right;
		
	public PlayerAI(Player p){
		player = p;
		right = false;
	}
		
	public void update(){}
		
	public void reactToKey(char key, boolean release){
		if(release){
			if(key == 'd' && right){
				((DynamicPhysicsData)(player.getPD())).getAcc().x = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().x = 0;
			}if(key == 'a' && !right){
				((DynamicPhysicsData)(player.getPD())).getAcc().x = 0;
				((DynamicPhysicsData)(player.getPD())).getVel().x = 0;
			}
		}else{
			if(key == 'd' && !player.getCollidingRight()){
				((DynamicPhysicsData)(player.getPD())).getAcc().x = .5f;
				right = true;
				player.setCollidingLeft(false);
			}if(key == 's' && !player.getCollidingDown()){
				player.setCollidingUp(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				((DynamicPhysicsData)(player.getPD())).getVel().y = ((DynamicPhysicsData)(player.getPD())).getMaxVel().y;
			}if(key == 'a' && !player.getCollidingLeft()){
				right = false;
				player.setCollidingRight(false);
				((DynamicPhysicsData)(player.getPD())).getAcc().x = -.5f;
			}if((key == 'w' || key == ' ') && !player.getCollidingUp() && !player.getJumping()){
				player.setCollidingDown(false);
				player.setCollidingRight(false);
				player.setCollidingLeft(false);
				player.setJumping(true);
				((DynamicPhysicsData)(player.getPD())).getVel().y = -5.0f;
			}
		}
	}
}
