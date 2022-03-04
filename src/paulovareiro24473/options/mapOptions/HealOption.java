package paulovareiro24473.options.mapOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;

public class HealOption extends Option{

	public HealOption() {
		super("Heal");
	}

	@Override
	public void execute() {
		Player player = StoryState.getPlayer();
		if(!player.usePotion()){
			if(player.getPotions() == 0){
				Logic.printHeading("You don't have any potions!");
			}else if(player.getHP() == player.getMaxHP()){
				Logic.printHeading("You are already maximum HP!","Potions left: " + player.getPotions());
			}
			Logic.getch();
			return;
		}
		Logic.printHeading("You used a potion! +" + player.getPotionHeal() + "HP","Potions left: " + player.getPotions());
		Logic.getch();
	}

}
