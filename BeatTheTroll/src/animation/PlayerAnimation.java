package animation;

import entities.Entity;
import entities.Player;

public class PlayerAnimation extends Animation{
	public Player player;
	
	public PlayerAnimation(Entity en){
		super(en);
		player = (Player)en;
	}
	
	public void update(){
		
	}
	
	//public void draw(Graphics2D g){
	//	
	//}
}
