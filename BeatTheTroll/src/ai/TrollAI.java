package ai;

import entities.Blast;
import entities.Hammer;
import entities.Hole;
import entities.Player;
import entities.Troll;
import math.Vec2;
import misc.RNG;
import physics.DynamicPhysicsData;

public class TrollAI implements ReactToPlayerAI{
	public Troll troll;
	public float accuracy;
	public float chanceMoveX; // out of 100
	public float chanceMoveY; // out of 100
	public float chanceAttackPlayer; // out of 100
	public float chanceHole; // out of 100
	public float chanceTroll;
	//public int movingTime;
	public int activityTimer;
	public int timer;
	public int fireTimer;
	public int hammerTimer;
	public int blastTimer;
	public boolean moveAlongY;
	public boolean moveAlongX;
	public boolean moveAlongBoth;
	public boolean moveToSpace;
	public boolean throwing;
	public boolean shooting;
	public boolean trolling;
	public boolean genHoles;
	public boolean active;
	public boolean gone;
	public Vec2 moveTo;
	public Vec2 vel;
	public float speed;
	
	public TrollAI(Troll param){
		troll = param;
		chanceMoveX = 40;
		chanceMoveY = 40;
		chanceAttackPlayer = 15;
		chanceHole = 5;
		moveAlongX = false;
		moveAlongY = false;
		moveAlongBoth = false;
		moveToSpace = false;
		shooting = false;
		throwing = false;
		trolling = false;
		genHoles = false;
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
		moveAlongBoth = false;
		moveToSpace = false;
		throwing = false;
		shooting = false;
		trolling = false;
		genHoles = false;
		active = false;
	}
	
	public void update(){
		//System.out.println(troll.pd.pos);
		//System.out.println(gone);
		if(!gone){
			if(throwing){
				fireTimer--;
				if(fireTimer <= 0){
					// fire hammer
					Vec2 p = new Vec2(troll.pd.pos.x + troll.pd.siz.x/2,troll.pd.pos.y + troll.pd.siz.y/2);
					Hammer h = new Hammer(troll.ref, p);
					h.damage = 50 * troll.level;
					troll.ref.gameEntities.add(h);
					fireTimer = hammerTimer;
				}
			}
			if(shooting){
				fireTimer--;
				if(fireTimer <= 0){
					// fire blast
					Vec2 p = new Vec2(troll.pd.pos.x + troll.pd.siz.x/2,troll.pd.pos.y + troll.pd.siz.y/2);
					Blast b = new Blast(troll.ref,p,false);
					b.damage = 100 * troll.level;
					troll.ref.gameEntities.add(b);
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
			//System.out.println(active);
			if(active){
				timer--;
				if(moveAlongX){
					//System.out.println("Moving On X");
					//System.out.println(troll.pd.pos.x);
					if(!moveToSpace){
						if(troll.pd.pos.x <= 100){
							//System.out.println("WHAT");
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
							tmp.vel = new Vec2(speed,0);
						}
						else if(troll.pd.pos.x >= 700){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
							tmp.vel = new Vec2(-speed,0);
						}else{
							// does nothing
						}
					}else{
						Vec2 dist = new Vec2(moveTo.x - troll.pd.pos.x,moveTo.y - troll.pd.pos.y);
						Vec2 distNorm = dist.norm();
						DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
						tmp.vel = new Vec2(distNorm.x * speed, distNorm.y * speed);
						//System.out.println("WHAT");
						//System.out.println(dist.exDist(tmp.pos));
						//System.out.println(moveTo);
						//System.out.println(tmp.pos);
						float x = moveTo.x - troll.pd.pos.x;
						float y = moveTo.y - troll.pd.pos.y;
						if(x*x+y*y <= 100){
							moveToSpace = false;
							//DynamicPhysicsData tmp2 = (DynamicPhysicsData)troll.pd;
							tmp.vel = new Vec2(0,0);
							//moveTo = new Vec2();
						}
					}
				}else if(moveAlongY){
					//System.out.println("Moving On Y");
					if(!moveToSpace){
						//System.out.println(troll.pd.pos);
						if(troll.pd.pos.y <= 150){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
							tmp.vel = new Vec2(0,speed);
						}
						else if(troll.pd.pos.y >= 500){
							DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
							tmp.vel = new Vec2(0,-speed);
						}else{
							// does nothing
						}
					}else{
						Vec2 dist = new Vec2(moveTo.x - troll.pd.pos.x,moveTo.y - troll.pd.pos.y);
						Vec2 distNorm = dist.norm();
						DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
						tmp.vel = new Vec2(distNorm.x * speed, distNorm.y * speed);
						//System.out.println("WHAT");
						//System.out.println(dist.exDist(tmp.pos));
						//System.out.println(moveTo);
						//System.out.println(tmp.pos);
						float x = moveTo.x - troll.pd.pos.x;
						float y = moveTo.y - troll.pd.pos.y;
						if(x*x+y*y <= 100){
							moveToSpace = false;
							//DynamicPhysicsData tmp2 = (DynamicPhysicsData)troll.pd;
							shooting = true;
							throwing = false;
							tmp.vel = new Vec2(0,0);
							//moveTo = new Vec2();
						}
						//if(dist.exDist(tmp.pos) <= 1600){
						//	moveToSpace = false;
						//	//moveTo = new Vec2();
						//	shooting = true;
						//	throwing = false;
						//}
					}
				}else if(moveAlongBoth){
					//System.out.println("WHAT");
				}else if(moveToSpace){
					//System.out.println("Moiving ToSpace");
					Vec2 dist = new Vec2(moveTo.x - troll.pd.pos.x,moveTo.y - troll.pd.pos.y);
					Vec2 distNorm = dist.norm();
					DynamicPhysicsData tmp = (DynamicPhysicsData)troll.pd;
					tmp.vel = new Vec2(distNorm.x * speed, distNorm.y * speed);
					//System.out.println("WHAT");
					//System.out.println(dist.exDist(tmp.pos));
					//System.out.println(moveTo);
					//System.out.println(tmp.pos);
					//System.out.println(dist.exDist(tmp.pos));
					float x = moveTo.x - troll.pd.pos.x;
					float y = moveTo.y - troll.pd.pos.y;
					if(x*x+y*y <= 100){
						moveToSpace = false;
						//System.out.println("HAHA");
						//DynamicPhysicsData tmp2 = (DynamicPhysicsData)troll.pd;
						//shooting = true;
						throwing = false;
						active = false;
						tmp.vel = new Vec2(0,0);
						//System.out.println("WHAT THE FLIPJACK");
						//moveTo = new Vec2();
					}
					//if(dist.exDist(tmp.pos) <= 1600){
					//	moveToSpace = false;
					//	//moveTo = new Vec2();
					//	throwing = false;
					//	active = false;
					//}
				}else{
					//System.out.println("Hey Listen");
				}
				if(timer <= 0)
					active = false;
			}else{
				float chance = RNG.getNextFloat() * 100;
				//System.out.println(chance);
				//System.out.println(chanceHole);
				//System.out.println(troll.level);
				float tmp = 0;
				//if(chance > tmp && chance < tmp + chanceHole){
				//	System.out.println("HOLEOLEOLEOLEOLE");
				//	troll.ref.gameEntities.add(new Hole(troll.ref,new Vec2(80,480),false));
				//	return;
				//}
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
					//moveAlongY = true;
					moveToSpace = true;
					throwing = true;
					moveTo = troll.ref.player.pd.pos;
					timer = activityTimer;
					return;
				}
				tmp+=chanceAttackPlayer;
				if(chance > tmp && chance < tmp + chanceHole){
					//System.out.println("HOLEOLEOLEOLEOLE");
					troll.ref.gameEntities.add(new Hole(troll.ref,new Vec2(80,700),false));
					return;
				}
			}
		}
	}
	
	public void setChances(int level){
		if(level == 1){
			if(troll.level == 1){
				accuracy = 0.0f;
				chanceMoveX = 80.0f;
				chanceMoveY = 10.0f;
				//chanceMoveX = 0.0f;
				//chanceMoveY = 00.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 5.0f;
				chanceTroll = 0.0f;
				activityTimer = 400;
				fireTimer = 80;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.level == 2){
				accuracy = 0.0f;
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 20.0f;
				chanceHole = 10.0f;
				chanceTroll = 0.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 50;
				blastTimer = 65;
				speed = 4f;
			}if(troll.level == 3){
				accuracy = 0.0f;
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 15.0f;
				chanceHole = 15.0f;
				chanceTroll = 0.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 30;
				blastTimer = 50;
				speed = 5f;
			}if(troll.level == 4){
				accuracy = 0.0f;
				chanceMoveX = 52.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 26.0f;
				chanceHole = 22.0f;
				chanceTroll = 0.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 10;
				blastTimer = 30;
				speed = 6f;
			}
		}
		if(level == 2){
			if(troll.level == 1){
				accuracy = 0.0f;
				chanceMoveX = 10.0f;
				chanceMoveY = 85.0f;
				//chanceMoveX = 0.0f;
				//chanceMoveY = 100.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 400;
				fireTimer = 100;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.level == 2){
				accuracy = 0.0f;
				chanceMoveX = 10.0f;
				chanceMoveY = 60.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 65;
				blastTimer = 50;
				speed = 4f;
			}if(troll.level == 3){
				accuracy = 0.0f;
				chanceMoveX = 10.0f;
				chanceMoveY = 60.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 50;
				blastTimer = 30;
				speed = 5f;
			}if(troll.level == 4){
				accuracy = 0.0f;
				chanceMoveX = 70.0f;
				chanceMoveY = 0.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 30;
				blastTimer = 10;
				speed = 6f;
			}
		}
		if(level == 3){
			if(troll.level == 1){
				accuracy = 0.0f;
				chanceMoveX = 85.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 5.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 400;
				fireTimer = 80;
				hammerTimer = 80;
				blastTimer = 80;
				speed = 3f;
			}if(troll.level == 2){
				accuracy = 0.0f;
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 320;
				fireTimer = 50;
				hammerTimer = 50;
				blastTimer = 65;
				speed = 4f;
			}if(troll.level == 3){
				accuracy = 0.0f;
				chanceMoveX = 60.0f;
				chanceMoveY = 10.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 250;
				fireTimer = 40;
				hammerTimer = 30;
				blastTimer = 50;
				speed = 5f;
			}if(troll.level == 4){
				accuracy = 0.0f;
				chanceMoveX = 70.0f;
				chanceMoveY = 0.0f;
				chanceAttackPlayer = 30.0f;
				chanceHole = 0.0f;
				chanceTroll = 0.0f;
				activityTimer = 180;
				fireTimer = 20;
				hammerTimer = 10;
				blastTimer = 30;
				speed = 6f;
			}
		}
	}
	
	public void reactToPlayer(Player player){
		
	}
}
