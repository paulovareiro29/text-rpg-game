package paulovareiro24473.options.shopOptions.options.upgrades;

import paulovareiro24473.Logic;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;

public class MaxPotionUpgrade extends Upgrade{

	public MaxPotionUpgrade(String name,int availableFrom, int cost) {
		super(name + availableFrom + "]", new String[]{"maxpotions"},1, availableFrom, cost);
	}

	@Override
	public void upgrade() {
		Player p = StoryState.getPlayer();
		p.setMaxPotions(p.getMaxPotions()+1);
		
		
		Logic.printHeading("You have upgraded your maximum potions!","Maximum potions: " + p.getMaxPotions());
		Logic.getch();
	}

}
