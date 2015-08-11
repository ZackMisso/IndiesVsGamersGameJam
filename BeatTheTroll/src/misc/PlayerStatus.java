// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package misc;

import java.util.ArrayList;

//import entities.Item;

public class PlayerStatus {
	//private ArrayList<Item> items;
	private int score;
	private int level;
	private int timeTillAttack;
	private int timeOne;
	private int timeTwo;
	private int timeThree;
	private int timeFour;
	private boolean gameOver;
	private boolean canRestart;
	
	public PlayerStatus(){
		//items = new ArrayList<>();
		score = 500000;
		gameOver = false;
		canRestart = false;
	}
	
	public void addToScore(int val){
		score += val;
	}
	
	public void initToOne(){
		timeOne = 60*60*2;
		timeTillAttack = timeOne;
	}
	
	public void initToTwo(){
		timeTwo = 60*60*2;
		timeTillAttack = timeTwo;
	}
	
	public void initToThree(){
		timeThree = 60*60*2;
		timeTillAttack = timeThree;
	}
	
	public void initToFour(){
		timeFour = 60*60*2;
		timeTillAttack = timeFour;
	}
	
	public void decrementTimeTillAttack(){
		timeTillAttack--;
	}
	
	// getter methods
	public int getScore(){return score;}
	public int getLevel(){return level;}
	public int getTimeTillAttack(){return timeTillAttack;}
	public boolean getGameOver(){return gameOver;}
	public boolean getCanRestart(){return canRestart;}
	
	// setter methods
	public void setScore(int param){score = param;}
	public void setLevel(int param){level = param;}
	public void setGameOver(boolean param){gameOver = param;}
	public void setCanRestart(boolean param){canRestart = param;}
	public void setTimeTillAttack(int param){timeTillAttack=param;}
}
