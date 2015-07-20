package animation;

import entities.Entity;
import gameState.GameStateManager;

public class CloudAnimation extends Animation{
	public GameStateManager gsm;
	
	public CloudAnimation(GameStateManager ref){
		super(null);
		gsm = ref;
		images.add(gsm.parser.getImage("Cloud"));
	}
	
	public void update(){
		
	}
}
