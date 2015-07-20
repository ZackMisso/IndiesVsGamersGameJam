package ai;

import entities.ItemUI;

public class ItemUIAI implements ReactToInputAI {
	public ItemUI item;
	
	public ItemUIAI(ItemUI param){
		item = param;
	}
	
	public void update(){}
	
	public void reactToMouse(int x, int y){
		
	}
	
	public void reactToKey(char key, boolean release){
		// maybe?
	}
}
