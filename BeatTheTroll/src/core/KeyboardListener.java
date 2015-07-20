package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	public GameThread thread;
	
	public KeyboardListener(GameThread param){
		thread = param;
	}
	
	public void keyPressed(KeyEvent event){
		thread.gsm.handleKey(event.getKeyChar(), false);
	}
	
	public void keyReleased(KeyEvent event){
		thread.gsm.handleKey(event.getKeyChar(), true);
	}
	
	public void keyTyped(KeyEvent event){
		//keyPressed(event);
	}
}
