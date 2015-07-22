// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEngine {
	private int timer;
	private int timeForIntro;
	private int timeForMain;
	private int timeForEpic;
	private boolean intro;
	private boolean main;
	private boolean epic;
	private boolean notSupported;
	private Song currentSong;
	
	public SoundEngine(){
		timeForIntro = 27 + 60 * 28; // 28.44
		timeForMain = 4 * 60 * 60 + 46 * 60; // 4:45:33
		timeForEpic = 60 * 60 + 40 * 60; // 1:39:55
		timer = timeForIntro;
		intro = false;
		main = false;
		epic = false;
		notSupported = false;
	}
	
	public void update(){
		if(!notSupported){
			if(intro){
				if(currentSong.finished)
					switchToMain();
			}else if(main){
				if(currentSong.finished)
					switchToEpic();
			}else if(epic){
				if(currentSong.finished)
					switchToMain();
			}
		}
	}
	
	public void switchToIntro(){
		intro = true;
		main = false;
		epic = false;
		try{
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Zack Video Game 1.wav"));
			Clip clip = AudioSystem.getClip();
	    	clip.open(audioInputStream);
	    	timer = timeForIntro;
	    	currentSong = new Song();
	    	clip.addLineListener(currentSong);
	    	clip.start();
	    }
	    catch(Exception ex){
	    	try{
	    		AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Menumusic.wav"));
				Clip clip = AudioSystem.getClip();
		    	clip.open(audioInputStream);
		    	timer = timeForIntro;
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
	    		//{System.err.println(ex2);}
		    	notSupported = true;
	    	}
	    }
	}
	
	public void switchToMain(){
		intro = false;
		main = true;
		epic = false;
		try{
			timer = timeForMain;
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Zack Video Game 2.wav"));
	    	Clip clip = AudioSystem.getClip();
	    	clip.open(audioInputStream);
	    	currentSong = new Song();
	    	clip.addLineListener(currentSong);
	    	clip.start();
	    }
		catch(Exception ex){
	    	try{
	    		AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Maintheme.wav"));
				Clip clip = AudioSystem.getClip();
		    	clip.open(audioInputStream);
		    	timer = timeForMain;
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
	    		//{System.err.println(ex2);}
		    	notSupported = true;
	    	}
	    }
	}
	
	public void switchToEpic(){
		intro = false;
		main = false;
		epic = true;
		try{
			timer = timeForEpic;
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Zack Video Game 3.wav"));
	    	Clip clip = AudioSystem.getClip();
	    	clip.open(audioInputStream);
	    	currentSong = new Song();
	    	clip.addLineListener(currentSong);
	    	clip.start();
	    }
		catch(Exception ex){
	    	try{
	    		AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("FinalBoss.wav"));
				Clip clip = AudioSystem.getClip();
		    	clip.open(audioInputStream);
		    	timer = timeForEpic;
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
	    		//{System.err.println(ex2);}
		    	notSupported = true;
	    	}
	    }
	}
}
