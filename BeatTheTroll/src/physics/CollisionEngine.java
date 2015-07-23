// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

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
	
	public void update(Entity player, Entity troll, ArrayList<Entity> entities){
		if(player != null){
			ArrayList<Manifold> mans = findCollisions(player, entities);
			resolveManifolds(mans);
		}if(troll != null){
			ArrayList<Manifold> mans = new ArrayList<>();
			Manifold man = new Manifold(player, troll);
			mans.add(man);
			resolveManifolds(mans);
		}
	}
	
	public Manifold checkCollisionAABB(Entity one, Entity two){
		if(one.getPD().getPos().x + one.getPD().getSiz().x < two.getPD().getPos().x || one.getPD().getPos().x > two.getPD().getPos().x + two.getPD().getSiz().x)
			return null;
		if(one.getPD().getPos().y + one.getPD().getSiz().y < two.getPD().getPos().y || one.getPD().getPos().y > two.getPD().getPos().y + two.getPD().getSiz().y)
			return null;
		return new Manifold(one, two);
	}
	
	public ArrayList<Manifold> findCollisions(Entity entity, ArrayList<Entity> entities){
		ArrayList<Manifold> mans = new ArrayList<>();
		for(int i=0;i<entities.size();i++){
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
			Entity one = folds.get(i).getOne();
			Entity two = folds.get(i).getTwo();
			Vec2 n = one.getPD().cent().add(two.getPD().cent().neg(true), true);
			float oneExtent = one.getPD().getSiz().x / 2;
			float twoExtent = two.getPD().getSiz().x / 2;
			float xOverlap = oneExtent + twoExtent - Functions.abs(n.x);
			if(xOverlap > 0){
				oneExtent = one.getPD().getSiz().y / 2;
				twoExtent = two.getPD().getSiz().y / 2;
				float yOverlap = twoExtent + oneExtent - Functions.abs(n.y);
				if(yOverlap > 0){
					if(Functions.abs(xOverlap) >= Functions.abs(yOverlap)){
						Manifold fold = folds.get(i);
						if(n.y > 0)
							fold.setNorm(new Vec2(0, 1));
						else
							fold.setNorm(new Vec2(0, -1));
						fold.setPen(new Vec2(0,yOverlap));
						one.handleManifold(fold);
						two.handleManifold(fold);
					}else{
						Manifold fold = folds.get(i);
						if(n.x < 0)
							fold.setNorm(new Vec2(-1, 0));
						else
							fold.setNorm(new Vec2(1, 0));
						fold.setPen(new Vec2(xOverlap,0));
						one.handleManifold(fold);
						two.handleManifold(fold);
					}
				}
			}
		}
	}
}
