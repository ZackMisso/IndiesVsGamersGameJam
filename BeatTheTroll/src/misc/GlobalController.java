// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package misc;

import java.util.ArrayList;

import org.gamejolt.GameJoltAPI;

// All login information for Game Jolt will be saved as global variables for now.

public class GlobalController {
	public static boolean loggedIn = false;
	public static String userName = "";
	public static String privateKey = "";
	public static int GAME_ID = 80394;
	public static String GAME_SECRET = "";
	public static GameJoltAPI api = null;
	public static int TABLEID = 83344;
	
	public static ArrayList<String> logIntoGameJolt(){
		ArrayList<String> msgs = new ArrayList<String>();
		api = new GameJoltAPI(GAME_ID, GAME_SECRET);
		//api.setVerbose(true);
		if(api.verifyUser(userName, privateKey)){
			//System.out.println("LOGGED IN");
			loggedIn = true;
			//addHighScore();
		}else{
			msgs.add("The User name and/or token were invalid.");
			msgs.add("The game will run but your score will not be posted");
		}
		return msgs;
	}
	
	public static void addHighScore(int score){
		if(api != null)
			if(api.verifyUser(userName, privateKey))
				api.addHighscore(score+" Points",score);
	}
}
