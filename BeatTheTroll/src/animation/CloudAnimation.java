// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package animation;

import entities.Entity;
import gameState.GameStateManager;

public class CloudAnimation extends Animation{
	private GameStateManager gsm;
	
	public CloudAnimation(GameStateManager ref){
		super(null);
		gsm = ref;
		images.add(gsm.parser.getImage("Cloud"));
	}
	
	public void update(){}
}
