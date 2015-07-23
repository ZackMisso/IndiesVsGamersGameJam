// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import ai.ReactToInputAI;
import animation.LimitAnimation;
import entities.Entity;
import entities.Player;
import entities.Troll;
import math.Vec2;
import physics.CollisionEngine;

public abstract class Level {
	private GameStateManager gsm;
	private Color bg;
	private ArrayList<Entity> gameEntities;
	private ArrayList<Entity> playerObj;
	private ArrayList<Entity> enemyObj;
	private ArrayList<ReactToInputAI> aiInput;
	private ArrayList<LimitAnimation> extraAnimations;
	private ArrayList<Entity> entitiesToRemove;
	private CollisionEngine cole;
	private Troll troll;
	private Player player;
	private boolean restart;
	
	public Level(GameStateManager g,Player p, Troll t){
		gsm = g;
		player = p;
		if(p!=null)
			player.initForLevel(this);
		troll = t;
		if(t != null)
			troll.initForLevel(this);
		bg = null;
		gameEntities = new ArrayList<>();
		playerObj = new ArrayList<>();
		enemyObj = new ArrayList<>();
		aiInput = new ArrayList<>();
		extraAnimations = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
		cole = new CollisionEngine();
		restart = false;
	}
	
	public void updateCollisionEngine(){
		cole.update(player, troll, gameEntities);
	}
	
	public void updateEntities(){
		for(int i=0;i<gameEntities.size();i++)
			gameEntities.get(i).update();
	}
	
	public void updateExtraAnimations(){
		for(int i=0;i<extraAnimations.size();i++){
			extraAnimations.get(i).update();
			if(extraAnimations.get(i).checkRemove())
				extraAnimations.remove(i--);
		}
	}
	
	public void updateEntitiesToRemove(){
		for(int i = 0;i<entitiesToRemove.size();i++){
			gameEntities.remove(entitiesToRemove.get(0));
			entitiesToRemove.remove(0);
		}
	}
	
	public void addEntity(Entity en){
		gameEntities.add(en);
	}
	
	public void addExtraAnimation(LimitAnimation param){
		extraAnimations.add(param);
	}
	
	public void addEntityToRemove(Entity en){
		entitiesToRemove.add(en);
	}
	
	public void addInputAI(ReactToInputAI ai){
		aiInput.add(ai);
	}
	
	public abstract void init();
	public abstract void update();
	
	public void draw(Graphics2D g){
		if(bg != null){
			g.setColor(bg);
			g.fillRect(81, 121, 718, 478);
		}
		for(int i=0;i<gameEntities.size();i++)
			gameEntities.get(i).draw(g);
		for(int i=0;i<extraAnimations.size();i++)
			extraAnimations.get(i).draw(g);
		for(int i=0;i<playerObj.size();i++)
			playerObj.get(i).draw(g);
		for(int i=0;i<enemyObj.size();i++)
			enemyObj.get(i).draw(g);
		if(troll != null)
			troll.draw(g);
		if(player != null)
			player.draw(g);
	}
	
	public void handleClick(int x, int y){} // The game ended up not using Mouse clicks
	
	public void handleKey(char key, boolean release){
		for(int i=0;i<aiInput.size();i++)
			aiInput.get(i).reactToKey(key, release);
	}
	
	// getter methods
	public GameStateManager getGSM(){return gsm;}
	public Player getPlayer(){return player;}
	public Troll getTroll(){return troll;}
	public Color getBG(){return bg;}
	public boolean getRestart(){return restart;}
	
	// setter methods
	public void setBG(Color param){bg=param;}
}
