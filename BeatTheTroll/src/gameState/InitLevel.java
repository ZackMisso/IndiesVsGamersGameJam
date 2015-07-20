// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entities.Player;
import entities.Troll;
import misc.GlobalController;

public class InitLevel extends Level{
	public String acept;
	public String userName;
	public String privateKey;
	public ArrayList<String> messages;
	public boolean accepting;
	public boolean addingName;
	public boolean addingKey;
	public boolean closing;
	public int timer;
	
	public InitLevel(GameStateManager gsm,Player p, Troll t){
		super(gsm,p,t);
		bg = Color.BLACK;
		acept = "";
		userName = "";
		privateKey = "";
		accepting = true;
		addingName = false;
		addingKey = false;
		closing = false;
		timer = 0;
	}
	
	public void init(){
		messages = new ArrayList<>();
		messages.add("Do you want to log into Game Jolt? (y for yes)");
	}
	
	public void update(){
		if(closing){
			timer++;
			if(timer >= 70)
				gsm.transition();
		}
	}
	
	public void logInToGameJolt(){
		// to be implemented
	}
	
	public void draw(Graphics2D g){
		if(bg != null){
			g.setColor(bg);
			g.fillRect(81, 121, 718, 478);
		}
		g.setColor(Color.GREEN);
		int i=0;
		for(;i<messages.size();i++){
			g.drawString(messages.get(i), 82, 171 + 20*i);
		}
		if(closing){
			
		}
		else if(addingKey){
			g.drawString(privateKey, 82, 171 + 20*i);
		}else if(addingName){
			g.drawString(userName, 82, 171 + 20*i);
		}else if(accepting){
			g.drawString(acept, 82, 171 + 20*i);
		}
	}
	
	public void handleClick(int x, int y){
		//for(int i=0;i<aiInput.size();i++)
		//	aiInput.get(i).reactToMouse(x, y);
	}
	
	public void handleKey(char key, boolean release){
		//System.out.println((int)key);
		if(!release){
			if(closing){
			
			}
			//if(accepting){
			//	if(key == '\n'){
			//		if(acept.equals("y")){
			//			addingName = true;
			//			messages.add(acept);
			//			messages.add("Enter in your user name");
			//		}
			//		else{
			//			closing = true;
			//			messages.add(acept);
		//			}
			//	}
			//	else if((int)key == 8 && acept.length() != 0){
		//			acept = acept.substring(0, acept.length()-1);
			//	}else{
			//		acept = acept+key;
		//		}
		else if(addingKey){
			if(key == '\n'){
				//if(acept.equals("y")){
				//	addingName = true;
				messages.add(privateKey);
				messages.add("Logging into GameJolt");
				//}
				//else{
				GlobalController.userName = userName;
				GlobalController.privateKey = privateKey;
				ArrayList<String> list = GlobalController.logIntoGameJolt();
				for(int i=0;i<list.size();i++)
					messages.add(list.get(i));
				closing = true;
				//	messages.add(acept);
				//}
			}
			else if((int)key == 8 && privateKey.length() != 0){
				privateKey = privateKey.substring(0, privateKey.length()-1);
			}else{
				//if(Character.isLetterOrDigit(codePoint))
				privateKey = privateKey+key;
			}
		}else if(addingName){
				if(key == '\n'){
					//if(userName.equals("y")){
					addingKey = true;
					messages.add(userName);
					messages.add("Enter in your private key associated with your account");
					//}
					//else{
					//	closing = true;
					//	messages.add(acept);
					//}
				}
				else if((int)key == 8 && userName.length() != 0){
					userName = userName.substring(0, userName.length()-1);
				}else{
					if(key != 65535)
					userName = userName+key;
				}
			}else if(accepting){
				if(key == '\n'){
					if(acept.equals("y")){
						addingName = true;
						messages.add(acept);
						messages.add("Enter in your user name");
					}
					else{
						closing = true;
						messages.add(acept);
					}
				}
				else if((int)key == 8 && acept.length() != 0){
					acept = acept.substring(0, acept.length()-1);
				}else{
					acept = acept+key;
				}
			}
		}
		//if(key == '\n'){
		//	
		//}
		//if((int)key == 8){
		//	// backspace
		//	
		//}
		//for(int i=0;i<aiInput.size();i++)
		//	aiInput.get(i).reactToKey(key, release);
	}
}
