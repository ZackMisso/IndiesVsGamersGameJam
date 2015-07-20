package gameState;

import ai.GameStartAI;
import entities.Player;
import entities.Troll;

public class TitleScreen extends Level{
	public TitleScreen(GameStateManager g,Player p, Troll t){
		super(g,p,t);
	}
	
	public void init(){
		aiInput.add(new GameStartAI(this));
	}
	
	public void update(){
		
	}
}
