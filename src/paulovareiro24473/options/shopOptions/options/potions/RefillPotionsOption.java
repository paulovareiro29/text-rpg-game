package paulovareiro24473.options.shopOptions.options.potions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.states.game.StoryState;

public class RefillPotionsOption extends Upgrade{

	public RefillPotionsOption() {
		super("Refill potions",new String[]{"refill"},99999,-1,50);
	}

	public void upgrade() {
		StoryState.getPlayer().setPotions(StoryState.getPlayer().getMaxPotions());
		
		
		Logic.printHeading("You refilled your potions");
		Logic.getch();
	}

}
