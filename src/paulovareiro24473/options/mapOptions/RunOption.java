package paulovareiro24473.options.mapOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.enemies.Enemy;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.maps.Map;

public class RunOption extends Option{

	protected Map map;
	
	public RunOption(Map map) {
		super("Run");
		this.map = map;
	}

	@Override
	public void execute() {
		Player player = StoryState.getPlayer();
		int chance  =  Logic.random(0,100);
		if(chance > 50){
			map.setCorage(false);
			Logic.printHeading("You are running from the fight!");			
		}else{
			Logic.printHeading("You failed to run from the fight!");
			
			Enemy enemy = map.getEnemies().get(0);
			enemy.attack(StoryState.getPlayer()); //inimigo ataca o jogador
			Logic.separatorln(Logic.lastSeperatorSize);

			if(player.isDead()){
				
				Logic.printHeading("You died to " + enemy.getName(),"You lost " + player.getGold()*0.2 + " gold and " + player.getExp()*0.2 +" experience");
				
				int gold = (int) Math.round(player.getGold() - player.getGold()*0.2);
				int exp = (int) Math.round(player.getExp() - player.getExp()*0.2);
				player.setGold(gold);
				player.setExp(exp);
				player.heal();
				map.setCorage(false);
				
			}
		}

		Logic.getch();
	}

}
