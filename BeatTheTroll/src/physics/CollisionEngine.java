package physics;

import java.util.ArrayList;

import entities.Entity;
import entities.Hole;
import math.Functions;
import math.Vec2;


public class CollisionEngine {
	private Manifold hack;
	
	public CollisionEngine(){
		hack = null;
	}
	
	//public ArrayList<Manifold>
	public void update(Entity player, Entity troll, ArrayList<Entity> entities){
		if(player != null){
			ArrayList<Manifold> mans = findCollisions(player, entities);
			resolveManifolds(mans);
		}if(troll != null){
			ArrayList<Manifold> mans = new ArrayList<>();
			Manifold man = new Manifold(player, troll);
			mans.add(man);
			resolveManifolds(mans);
		//	ArrayList<Manifold> mans = findCollisions(troll, entities);
		//	resolveManifolds(mans);
		}
	}
	
	public Manifold checkCollisionAABB(Entity one, Entity two){
		if(one.pd.pos.x + one.pd.siz.x < two.pd.pos.x || one.pd.pos.x > two.pd.pos.x + two.pd.siz.x)
			return null;
		if(one.pd.pos.y + one.pd.siz.y < two.pd.pos.y || one.pd.pos.y > two.pd.pos.y + two.pd.siz.y)
			return null;
		return new Manifold(one, two);
	}
	
	public ArrayList<Manifold> findCollisions(Entity entity, ArrayList<Entity> entities){
		ArrayList<Manifold> mans = new ArrayList<>();
		for(int i=0;i<entities.size();i++){
			//if(entities.get(i)instanceof Hole)
			//	System.out.println("Found Hole");
			if(entity != entities.get(i)){
				hack = checkCollisionAABB(entity, entities.get(i));
				if(hack != null)
					mans.add(hack);
			}
		}
		return mans;
	}
	
	public void resolveManifolds(ArrayList<Manifold> folds){
		for(int i=0;i<folds.size();i++){
			Entity one = folds.get(i).one;
			Entity two = folds.get(i).two;
			//if(folds.get(i).two instanceof Hole)
			//	System.out.println("Found Hole");
			Vec2 n = one.pd.cent().add(two.pd.cent().neg(true), true);
			float oneExtent = one.pd.siz.x / 2;
			float twoExtent = two.pd.siz.x / 2;
			float xOverlap = oneExtent + twoExtent - Functions.abs(n.x);
			if(xOverlap > 0){
				oneExtent = one.pd.siz.y / 2;
				twoExtent = two.pd.siz.y / 2;
				float yOverlap = twoExtent + oneExtent - Functions.abs(n.y);
				if(yOverlap > 0){
					//if(folds.get(i).two instanceof Hole)
					//	System.out.println("Found Hole");
					if(Functions.abs(xOverlap) >= Functions.abs(yOverlap)){
						Manifold fold = folds.get(i);
						if(n.y > 0)
							fold.norm = new Vec2(0, 1);
						else
							fold.norm = new Vec2(0, -1);
						fold.pen = new Vec2(0,yOverlap);
						one.handleManifold(fold);
						two.handleManifold(fold);
					}else{
						Manifold fold = folds.get(i);
						if(n.x < 0)
							fold.norm = new Vec2(-1, 0);
						else
							fold.norm = new Vec2(1, 0);
						fold.pen = new Vec2(xOverlap,0);
						one.handleManifold(fold);
						two.handleManifold(fold);
					}
				}
			}
		}
	}
}
