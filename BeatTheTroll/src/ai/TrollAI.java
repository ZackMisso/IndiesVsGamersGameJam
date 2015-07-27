// IndiesVsGamers Game Jam
// Beat The Troll v.0.1.0
// By: Zack Misso

package ai;

import entities.Blast;
import entities.Hammer;
import entities.Hole;
import entities.Troll;
import math.Vec2;
import misc.RNG;
import physics.DynamicPhysicsData;

public class TrollAI implements AI{
	private Troll troll;
	private float chanceMoveX; // out of 100
	private float chanceMoveY; // out of 100
	private float chanceAttackPlayer; // out of 100
	private float chanceHole; // out of 100
	private int activityTimer;
	private int timer;
	private int fireTimer;
	private int hammerTimer;
	private int blastTimer;
	private boolean moveAlongY;
	private boolean moveAlongX;
	private boolean moveToSpace;
	private boolean throwing;
	private boolean shooting;
	private boolean trolling;
	private boolean active;
	private boolean gone;
	private Vec2 moveTo;
	private float speed;
	
	public TrollAI(Troll param){
		troll = param;
		chanceMoveX = 40;
		chanceMoveY = 40;
		chanceAttackPlayer = 15;
		chanceHole = 5;
		moveAlongX = false;
		moveAlongY = false;
		moveToSpace = false;
		shooting = false;
		throwing = false;
		trolling = false;
		active = false;
		gone = false;
	}
	
	public void reset(){
		setAllFalse();
		gone = false;
		active = false;
	}
	
	public void setAllFalse(){
		moveAlongX = false;
		moveAlongY = false;
		moveToSpace = false;
		throwing = false;
		shooting = false;
		trolling = false;
		active = false;
	}
	
	public void update(){
		if(!gone){
			if(throwing){
				fireTimer--;
				if(fireTimer <= 0){
					// fire hammer
					Vec2 p = new Vec2(troll.getPD().getPos().x + troll.getPD().getSiz().x/2,troll.getPD().getPos().y + troll.getPD().getSiz().y/2);
					Hammer h = new Hammer(troll.getRef(), p);
					h.setDamage(50 * troll.getLevel());
					troll.getRef().addEntity(h);
					fireTimer = hammerTimer;
				}
			}
			if(shooting){
				fireTimer--;
				if(fireTimer <= 0){
					// fire blast
					Vec2 p = new Vec2(troll.getPD().getPos().x + troll.getPD().getSiz().x/2,troll.getPD().getPos().y + troll.getPD().getSiz().y/2);
					Blast b = new Blast(troll.getRef(),p,false);
					b.setDamage(100 * troll.getLevel());
					troll.getRef().addEntity(b);
					fireTimer = blastTimer;
				}
			}
			if(trolling){
				// troll
				fireTimer--;
				if(fireTimer <= 0){
					trolling = false;
					active = false;
				}
			}
			if(active){
				timer--;
				if(moveAlongX){
					if(!moveToSpace){
						if(troll.getPD().getPos().x <= 100){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
							tmp.setVel(new Vec2(speed,0));
						}
						else if(troll.getPD().getPos().x >= 700){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
							tmp.setVel(new Vec2(-speed,0));
						}
					}else{
						Vec2 dist = new Vec2(moveTo.x - troll.getPD().getPos().x,moveTo.y - troll.getPD().getPos().y);
						Vec2 distNorm = dist.norm();
						DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
						tmp.setVel(new Vec2(distNorm.x * speed, distNorm.y * speed));
						float x = moveTo.x - troll.getPD().getPos().x;
						float y = moveTo.y - troll.getPD().getPos().y;
						if(x*x+y*y <= 100){
							moveToSpace = false;
							tmp.setVel(new Vec2(0,0));
						}
					}
				}else if(moveAlongY){
					if(!moveToSpace){
						if(troll.getPD().getPos().y <= 150){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
							tmp.setVel(new Vec2(0,speed));
						}
						else if(troll.getPD().getPos().y >= 500){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
							tmp.setVel(new Vec2(0,-speed));
						}
					}else{
						Vec2 dist = new Vec2(moveTo.x - troll.getPD().getPos().x,moveTo.y - troll.getPD().getPos().y);
						Vec2 distNorm = dist.norm();
						DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
						tmp.setVel(new Vec2(distNorm.x * speed, distNorm.y * speed));
						float x = moveTo.x - troll.getPD().getPos().x;
						float y = moveTo.y - troll.getPD().getPos().y;
						if(x*x+y*y <= 100){
							moveToSpace = false;
							shooting = true;
							throwing = false;
							tmp.setVel(new Vec2(0,0));
						}
					}
				}else if(moveToSpace){
					Vec2 dist = new Vec2(moveTo.x - troll.getPD().getPos().x,moveTo.y - troll.getPD().getPos().y);
					Vec2 distNorm = dist.norm();
					DynamicPhysicsData tmp = (DynamicPhysicsData)troll.getPD();
					tmp.setVel(new Vec2(distNorm.x * speed, distNorm.y * speed));
					float x = moveTo.x - troll.getPD().getPos().x;
					float y = moveTo.y - troll.getPD().getPos().y;
					if(x*x+y*y <= 100){
						moveToSpace = false;
						throwing = false;
						active = false;
						tmp.setVel(new Vec2(0,0));
					}
				}
				if(timer <= 0)
					active = false;
			}else{
				float chance = RNG.getNextFloat() * 100;
				float tmp = 0;
				tmp+=chanceHole;
				if(chance > tmp && chance < tmp + chanceMoveX){
					setAllFalse();
					active = true;
					moveAlongX = true;
					moveToSpace = true;
					throwing = true;
					moveTo = new Vec2(90,140);
					timer = activityTimer;
					return;
				}
				tmp+=chanceMoveX;
				if(chance > tmp && chance < tmp + chanceMoveY){
					setAllFalse();
					active = true;
					moveAlongY = true;
					moveToSpace = true;
					throwing = true;
					moveTo = new Vec2(90,140);
					timer = activityTimer;
					return;
				}
				tmp+=chanceMoveY;
				if(chance > tmp && chance < tmp + chanceAttackPlayer){
					setAllFalse();
					active = true;
					moveToSpace = true;
					throwing = true;
					moveTo = troll.getRef().getPlayer().getPD().getPos();
					timer = activityTimer;
					return;
				}
				tmp+=chanceAttackPlayer;
				if(chance > tmp && chance < tmp + chanceHole){
					troll.getRef().addEntity(new Hole(troll.getRef(),new Vec2(80,700),false));
					return;
				}
			}
		}
	}
	
	public void setChances(int level){
		if(level == 1){
			if(troll.getLevel() == 1){
				chanceMoveX = 80.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 5.0f;
				activityTimer = 400;
				fireTimer = 80;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.getLevel() == 2){
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 20.0f;
				chanceHole = 10.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 50;
				blastTimer = 65;
				speed = 4f;
			}if(troll.getLevel() == 3){
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 15.0f;
				chanceHole = 15.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 30;
				blastTimer = 50;
				speed = 5f;
			}if(troll.getLevel() == 4){
				chanceMoveX = 52.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 26.0f;
				chanceHole = 22.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 10;
				blastTimer = 30;
				speed = 6f;
			}
		}
		if(level == 2){
			if(troll.getLevel() == 1){
				chanceMoveX = 10.0f;
				chanceMoveY = 85.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 0.0f;
				activityTimer = 400;
				fireTimer = 100;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.getLevel() == 2){
				chanceMoveX = 10.0f;
				chanceMoveY = 60.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 65;
				blastTimer = 50;
				speed = 4f;
			}if(troll.getLevel() == 3){
				chanceMoveX = 10.0f;
				chanceMoveY = 60.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 50;
				blastTimer = 30;
				speed = 5f;
			}if(troll.getLevel() == 4){
				chanceMoveX = 70.0f;
				chanceMoveY = 0.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 30;
				blastTimer = 10;
				speed = 6f;
			}
		}
		if(level == 3){
			if(troll.getLevel() == 1){
				chanceMoveX = 85.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 0.0f;
				activityTimer = 400;
				fireTimer = 80;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.getLevel() == 2){
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 50;
				blastTimer = 65;
				speed = 4f;
			}if(troll.getLevel() == 3){
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 30;
				blastTimer = 50;
				speed = 5f;
			}if(troll.getLevel() == 4){
				chanceMoveX = 70.0f;
				chanceMoveY = 0.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 10;
				blastTimer = 30;
				speed = 6f;
			}
		}
	}
	
	// setter methods
	public void setMoveTo(Vec2 param){moveTo=param;}
	public void setMoveToSpace(boolean param){moveToSpace=param;}
	public void setActive(boolean param){active=param;}
}
