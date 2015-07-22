// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package art;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageParser {
	private Map<String,Image> cache;
	
	public ImageParser(){
		cache = new HashMap<String,Image>();
	}
	
	public Image getImage(String loc){
		if(cache.containsKey(loc))
			return cache.get(loc);
		else{
			Image img;
			try{
				img = ImageIO.read(getClass().getResourceAsStream(loc+".png"));
				if(img == null){
					System.out.println("WAHT");
				}
			}catch(IOException e){
				System.out.println("Image Parser Error");
				return null;
			}
			cache.put(loc, img);
			return img;
		}
	}
	
	public ArrayList<Image> getImages(String loc,int count){
		ArrayList<Image> imgs = new ArrayList<>();
		for(int i=0;i<count;i++){
			if(cache.containsKey(loc))
				imgs.add(cache.get(loc));
			else{
				try{
					Image img = ImageIO.read(getClass().getResourceAsStream(loc+i+".png"));
					if(img == null){
						System.out.println("WAHT");
					}
					imgs.add(img);
				}catch(IOException e){
					System.out.println("Image Parser Error");
				}
			}
		}
		return imgs;
	}
	
	public void clearCache(){
		cache.clear();
	}
}
