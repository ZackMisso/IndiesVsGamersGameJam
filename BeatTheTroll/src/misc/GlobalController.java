package misc;

import java.util.ArrayList;

import org.gamejolt.GameJoltAPI;

public class GlobalController {
	public static boolean canMove = false;
	public static boolean animating = false;
	public static boolean running = true;
	public static boolean loggedIn = false;
	public static String userName = "";
	public static String privateKey = "";
	public static int timeTillAttack = 0;
	public static int GAME_ID = 80394;
	public static String GAME_SECRET = "f49f8eb3ad4321c2d35e9ae71e631134";
	public static String USER_NAME = "";
	public static String USER_TOKEN = "";
	public static GameJoltAPI api = null;
	public static int TABLEID = 83344;
	public static int timeOne = 0;
	public static int timeTwo = 0;
	public static int timeThree = 0;
	public static int timeFour = 0;
	public static int score = 500000;
	public static int level = 1;
	public static boolean gameOver = false;
	public static boolean canRestart = false;
	
	public static ArrayList<String> logIntoGameJolt(){
		ArrayList<String> msgs = new ArrayList<String>();
		USER_NAME = userName;
		USER_TOKEN = privateKey;
		api = new GameJoltAPI(GAME_ID, GAME_SECRET);
		//api.setVerbose(true);
		if(api.verifyUser(USER_NAME, USER_TOKEN)){
			//System.out.println("LOGGED IN");
			loggedIn = true;
			//addHighScore();
		}else{
			msgs.add("The User name and/or token were invalid.");
			msgs.add("The game will run but your score will not be posted");
		}
		return msgs;
	}
	
	public static void addHighScore(){
		if(api != null)
			if(api.verifyUser(USER_NAME,USER_TOKEN))
				api.addHighscore(score+" Points",score);
	}
	
	public static void initToOne(){
		timeOne = 60*60*2;
		timeTillAttack = timeOne;
	}
	
	public static void initToTwo(){
		timeTwo = 60*60*2;
		timeTillAttack = timeTwo;
	}
	
	public static void initToThree(){
		timeThree = 60*60*2;
		timeTillAttack = timeThree;
	}
	
	public static void initToFour(){
		timeFour = 60*60*2;
		timeTillAttack = timeFour;
	}
}
