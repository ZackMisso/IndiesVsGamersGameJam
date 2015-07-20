package misc;

import java.util.ArrayList;

import entities.Item;

public class PlayerStatus {
	public ArrayList<Item> items;
	public long score;
	
	public PlayerStatus(){
		items = new ArrayList<>();
		score = 1000000;
	}
}
