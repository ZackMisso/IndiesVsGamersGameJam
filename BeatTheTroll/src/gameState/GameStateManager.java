// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import ai.ReactToInputAI;
import art.ImageParser;
import audio.SoundEngine;
import core.Game;
import entities.Joystick;
import entities.Player;
import entities.Troll;
import entities.UIButton;
import math.Vec2;
import misc.PlayerStatus;
import tests.TestLevel;

public class GameStateManager {
	private ImageParser parser;
	private Level currentLevel;
	private Player player;
	private PlayerStatus status;
	private Troll troll;
	private SoundEngine sound;
	private Image bg;
	private Joystick joystick;
	private String progCred;
	private String musicCred;
	private UIButton startButton;
	private UIButton selectButton;
	private UIButton aButton;
	private UIButton bButton;
	private int level;
	
	public GameStateManager(Game param){
		parser = new ImageParser();
		player = new Player(this);
		troll = new Troll(this);
		status = new PlayerStatus();
		bg = parser.getImage("Background");
		progCred = "Art and Programming : Zack Misso";
		musicCred = "Music : Carson Carter";
		sound = new SoundEngine();
		level = 0;
		status.initToOne();
		startMusic();
		transitionToInit();
	}
	
	public void startMusic(){
		sound.switchToIntro();
	}
	
	public void setRefForStuff(){
		player.initForLevel(currentLevel);
		troll.initForLevel(currentLevel);
		joystick = new Joystick(currentLevel,new Vec2(70,507),new Vec2());
		startButton = new UIButton(currentLevel,new Vec2(300,595),new Vec2(),0);
		selectButton = new UIButton(currentLevel,new Vec2(440,595), new Vec2(),1);
		aButton = new UIButton(currentLevel,new Vec2(710,575), new Vec2(),2);
		bButton = new UIButton(currentLevel,new Vec2(635,600), new Vec2(),3);
		currentLevel.addInputAI((ReactToInputAI)joystick.getAI());
		currentLevel.addInputAI((ReactToInputAI)startButton.getAI());
		currentLevel.addInputAI((ReactToInputAI)selectButton.getAI());
		currentLevel.addInputAI((ReactToInputAI)aButton.getAI());
		currentLevel.addInputAI((ReactToInputAI)bButton.getAI());
	}
	
	public void update(){
		sound.update();
		if(currentLevel != null)
			currentLevel.update();
	}
	
	public void draw(Graphics2D g){
		if(currentLevel != null)
			currentLevel.draw(g);
		g.drawImage(bg,0,0,null);
		g.setColor(Color.GRAY);
		g.drawString(progCred,140,760);
		g.drawString(musicCred,600, 760);
		g.drawString("w,a,s,d,space - move", 20, 685);
		g.drawString("w, space - jump", 20, 705);
		g.drawString("r - restart", 20, 725);
		g.setColor(Color.YELLOW);
		g.drawString("Score :: " + status.getScore(), 90, 140);
		g.drawString("Time Left :: " + status.getTimeTillAttack()/60, 695, 140);
		joystick.draw(g);
		startButton.draw(g);
		selectButton.draw(g);
		aButton.draw(g);
		bButton.draw(g);
	}
	
	public void restart(){
		status.setScore(500000);
		status.setGameOver(false);
		player = new Player(this);
		troll = new Troll(this);
		level = 0;
		transition();
	}
	
	public void handleClick(int x, int y){
		if(currentLevel != null)
			currentLevel.handleClick(x,y);
	}
	
	public void handleKey(char key, boolean release){
		if(key == 'r' && status.getCanRestart())
			restart();
		if(currentLevel != null)
			currentLevel.handleKey(key, release);
	}
	
	public void transition(){
		status.setCanRestart(true);
		if(level == 0){
			level = 1;
			transitionToLevel1();
		}else if(level == 1){
			level = 2;
			transitionToLevel2();
		}else if(level == 2){
			level = 3;
			transitionToLevel3();
		}else if(level == 3){
			level = 1;
			transitionToLevel1();
		}
	}
	
	public void transitionToInit(){
		parser.clearCache();
		currentLevel = new InitLevel(this, null, null);
		currentLevel.init();
		setRefForStuff();
	}
	
	public void transitionToTest(){
		parser.clearCache();
		currentLevel = new TestLevel(this, null, troll);
		currentLevel.init();
		setRefForStuff();
	}
	
	public void transitionToLevel1(){
		parser.clearCache();
		currentLevel = new LevelOne(this, player, troll);
		currentLevel.init();
		setRefForStuff();
	}
	
	public void transitionToLevel2(){
		parser.clearCache();
		currentLevel = new LevelTwo(this, player, troll);
		currentLevel.init();
		setRefForStuff();
	}

	public void transitionToLevel3(){
		parser.clearCache();
		currentLevel = new LevelThree(this, player, troll);
		currentLevel.init();
		setRefForStuff();
	}
	
	// getter methods
	public PlayerStatus getStatus(){return status;}
	public ImageParser getParser(){return parser;}
}
