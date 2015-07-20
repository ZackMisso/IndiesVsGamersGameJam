// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package misc;

import java.util.Random;

public class RNG {
	public static float[] vals;
	public static int index;
	public static Random ran;
	
	public static void init(){
		ran = new Random();
		vals = new float[1024];
		index = 0;
	}
	
	public static float getNextFloat(){
		if(index != 0)
			return vals[index--];
		return ran.nextFloat();
	}
	
	public static void extraTime(){
		if(index != 19){
			vals[++index] = ran.nextFloat();
		}
	}
	
	public static boolean getNextBool(){
		if(getNextFloat() > .5f)
			return true;
		return false;
	}
}
