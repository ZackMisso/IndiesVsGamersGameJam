// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEngine {
	private boolean intro;
	private boolean main;
	private boolean epic;
	private boolean notSupported;
	private Song currentSong;
	
	public SoundEngine(){
		intro = false;
		main = false;
		epic = false;
		notSupported = false;
	}
	
	public void update(){
		if(!notSupported){
			if(intro){
				if(currentSong.getFinished())
					switchToMain();
			}else if(main){
				if(currentSong.getFinished())
					switchToEpic();
			}else if(epic){
				if(currentSong.getFinished())
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
	    	currentSong = new Song();
	    	clip.addLineListener(currentSong);
	    	clip.start();
	    }
	    catch(Exception ex){
	    	try{
	    		AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("Menumusic.wav"));
				Clip clip = AudioSystem.getClip();
		    	clip.open(audioInputStream);
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
		    	notSupported = true;
	    	}
	    }
	}
	
	public void switchToMain(){
		intro = false;
		main = true;
		epic = false;
		try{
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
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
		    	notSupported = true;
	    	}
	    }
	}
	
	public void switchToEpic(){
		intro = false;
		main = false;
		epic = true;
		try{
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
		    	currentSong = new Song();
		    	clip.addLineListener(currentSong);
		    	clip.start();
	    	}catch(Exception ex2){
		    	notSupported = true;
	    	}
	    }
	}
}
