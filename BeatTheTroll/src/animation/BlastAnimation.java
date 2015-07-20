package animation;

import entities.Blast;
import entities.Entity;

public class BlastAnimation extends Animation{
	public BlastAnimation(Entity en){
		super(en);
		if(((Blast)ref).reverse)
			images.add(en.ref.gsm.parser.getImage("BlastReverse"));
		else
			images.add(en.ref.gsm.parser.getImage("Blast"));
	}
	
	public void update(){
		
	}
}
