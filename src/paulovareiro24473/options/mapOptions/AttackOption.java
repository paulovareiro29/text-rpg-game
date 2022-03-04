package paulovareiro24473.options.mapOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.enemies.Enemy;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.maps.Map;

public class AttackOption extends Option{

	protected Map map;
	
	public AttackOption(Map map) {
		super("Attack", "att","atc","atk","atck");
		this.map = map;
	}

	@Override
	public void execute() {
		Enemy enemy = map.getEnemies().get(0);
		Player player = StoryState.getPlayer();
		
		player.attack(enemy);
		enemy.attack(player);
		
		Logic.cls();
		Logic.printHeading(player.getName() + " has attacked " + enemy.getName() + " for " + enemy.getLastDamageReceived(),
							enemy.getName() + " has attacked " + player.getName() + " for " + player.getLastDamageReceived());
		
		Logic.getch();
		
		if(enemy.isDead()){
			

			player.receiveEXP(enemy.getExp());
			player.receiveGold(enemy.getGold());
			map.getEnemiesLayout().get((map.getEnemiesLayout().size() - map.getEnemies().size())).setCompleted(true);
			
			Logic.printHeading("You have killed " + enemy.getName(),"You received " + enemy.getExp() + " experience + " + enemy.getGold() + " gold!");
			Logic.getch();
			
			map.getEnemies().remove(0);
			if(map.getEnemies().size() == 0){
				map.setCorage(false);
				Logic.printHeading("Congratulations!!");
				Logic.println("You have finished " + map.getName() + "!!");
				Logic.separatorln(Logic.lastSeperatorSize);
				Logic.getch();

			}
		}
		
		if(player.isDead()){
			
			Logic.printHeading("You died to " + enemy.getName(),"You lost " + player.getGold()*0.2 + " gold and " + player.getExp()*0.2 +" experience");
			
			int gold = (int) Math.round(player.getGold() - player.getGold()*0.2);
			int exp = (int) Math.round(player.getExp() - player.getExp()*0.2);
			player.setGold(gold);
			player.setExp(exp);
			player.heal();
			Logic.getch();
			map.setCorage(false);
		}
	}

}
