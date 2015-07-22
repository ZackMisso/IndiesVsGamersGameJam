// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package math;

public class Vec2 {
	// Since Vec2 is such an often used data structure and contains no critical data,
	// I am leaving the instance variables as public. This is to avoid the overhead
	// caused by the method calls to the accessors and modifiers if they were implemented
	// instead
	public float x;
	public float y;
	
	public Vec2(){
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vec2(float a, float b){
		x = a;
		y = b;
	}
	
	public Vec2 vec(){
		return new Vec2(x,y);
	}
	
	public void add(Vec2 vec){
		x += vec.x;
		y += vec.y;
	}
	
	public Vec2 add(Vec2 vec, boolean hack){
		return new Vec2(x + vec.x, y + vec.y);
	}
	
	public float exDist(Vec2 one){
		float a = one.x - x;
		float b = one.y - y;
		return a*a + b*b;
	}
	
	public void neg(){
		x = -x;
		y = -y;
	}
	
	public Vec2 norm(){
		float temp = x*x + y*y;
		temp = (float)Math.sqrt((double)temp);
		if(temp == 0)
			return new Vec2();
		return new Vec2(x/temp,y/temp);
	}
	
	public void norm(boolean hack){
		float temp = x*x + y*y;
		temp = (float)Math.sqrt((double)temp);
		if(temp == 0){
			x = 0;
			y = 0;
		}else{
			x = x / temp;
			y = y / temp;
		}
	}
	
	public Vec2 neg(boolean hack){
		return new Vec2(-x,-y);
	}
	
	public float dot(Vec2 vec){
		return x*vec.x + y*vec.y;
	}
	
	public String toString(){ // used for debugging
		return "X :: "+x+" Y :: "+y;
	}
}
