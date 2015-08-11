// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private GameThread thread;
	
	public KeyboardListener(GameThread param){
		thread = param;
	}
	
	public void keyPressed(KeyEvent event){
		thread.getGSM().handleKey(event.getKeyChar(), false);
	}
	
	public void keyReleased(KeyEvent event){
		thread.getGSM().handleKey(event.getKeyChar(), true);
	}
	
	public void keyTyped(KeyEvent event){}
}
