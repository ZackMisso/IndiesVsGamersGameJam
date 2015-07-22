// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;
import entities.Player;

public class PlayerAnimation extends Animation{
	private Player player;
	
	public PlayerAnimation(Entity en){
		super(en);
		player = (Player)en;
	}
	
	public void update(){}
	
	//public void draw(Graphics2D g){
	//	
	//}
}
