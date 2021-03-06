// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package core;

import javax.swing.JFrame;

public class Game {
	private final JFrame window;
	private final GameThread thread;
	private final KeyboardListener key;
	//public final MousepadListener mouse; // never used
	
	public Game(int x, int y, String title){
		window = new JFrame();
		window.setSize(x,y);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		thread = new GameThread(this);
		key = new KeyboardListener(thread);
		//mouse = new MousepadListener(thread);
		window.add(thread);
		window.addKeyListener(key);
		//window.addMouseListener(mouse);
		new Thread(thread).start();
	}
	
	public JFrame getWindow(){return window;}
}
