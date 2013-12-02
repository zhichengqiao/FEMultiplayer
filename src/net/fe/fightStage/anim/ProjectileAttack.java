package net.fe.fightStage.anim;

import net.fe.fightStage.FightStage;
import chu.engine.TextureData;

public class ProjectileAttack extends AttackAnimation{
	private boolean finished;
	public ProjectileAttack(TextureData data, FightStage stage,
			AnimationArgs animArgs) {
		super(data, stage, animArgs);
	}
	
	public void done(){
		try{
			super.done();
		} catch (IllegalArgumentException e){
			//Drats! We'll try again next frame.
			finished = true;
		}
		
	}
	
	public void onStep(){
		if(finished){
			try{
				stage.setCurrentEvent(FightStage.DONE);
				finished = false;
			} catch (IllegalArgumentException e){
				//Foiled again! We'll try again next frame.
				finished = true;
			}
		}
	}

	@Override
	public void onHit() {
		stage.addEntity(new Projectile(
				animationArgs.wepAnimName, 
				FightStage.FLOOR - 50,
				stage, animationArgs.left, 
				animationArgs.wepAnimName.equals("javelin")));
	}

}
