package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ai.ReactToInputAI;
import ai.ReactToPlayerAI;
import animation.LimitAnimation;
import entities.Entity;
import entities.Player;
import entities.Troll;
import math.Vec2;

public abstract class Level {
	public GameStateManager gsm;
	public Color bg;
	public ArrayList<Entity> gameEntities;
	public ArrayList<Entity> playerObj;
	public ArrayList<Entity> enemyObj;
	public ArrayList<ReactToInputAI> aiInput;
	public ArrayList<ReactToPlayerAI> aiPlayer;
	public ArrayList<LimitAnimation> extraAnimations;
	public ArrayList<Entity> entitiesToRemove;
	public Troll troll;
	public Player player;
	public Vec2 offset;
	public boolean restart;
	
	public Level(GameStateManager g,Player p, Troll t){
		gsm = g;
		player = p;
		if(p!=null)
		player.ref = this;
		troll = t;
		if(t != null)
		troll.ref = this;
		bg = null;
		offset = new Vec2();
		gameEntities = new ArrayList<>();
		playerObj = new ArrayList<>();
		enemyObj = new ArrayList<>();
		aiInput = new ArrayList<>();
		aiPlayer = new ArrayList<>();
		extraAnimations = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
		restart = false;
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
	
	public void handleClick(int x, int y){
		for(int i=0;i<aiInput.size();i++)
			aiInput.get(i).reactToMouse(x, y);
	}
	
	public void handleKey(char key, boolean release){
		for(int i=0;i<aiInput.size();i++)
			aiInput.get(i).reactToKey(key, release);
	}
}
