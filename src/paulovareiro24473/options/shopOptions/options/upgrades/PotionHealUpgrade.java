package paulovareiro24473.options.shopOptions.options.upgrades;

import paulovareiro24473.Logic;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;

public class PotionHealUpgrade extends Upgrade{

	
	public PotionHealUpgrade(String name, int availableFrom, int cost) {
		super(name + availableFrom + "]",new String[]{"potionheal","heal upgrade"}, 1, availableFrom, cost);
	}

	@Override
	public void upgrade() {
		Player p = StoryState.getPlayer();
		p.setPotionHeal(p.getPotionHeal() * 3);
		
		
		Logic.printHeading("You have upgraded your Heal per Potion!","Heal per Potion: " + p.getPotionHeal());
		Logic.getch();
		
	}

}
