package paulovareiro24473.options.shopOptions.options.potions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.states.game.StoryState;

public class HealOption extends Upgrade{

	public HealOption() {
		super("Heal", new String[]{"heal hp"}, 9999, -1, 10);
	}

	@Override
	public void upgrade() {
		StoryState.getPlayer().heal();	
		
		
		Logic.printHeading("You have been healed","HP: " + StoryState.getPlayer().getHP() +"/"+StoryState.getPlayer().getMaxHP());
		Logic.getch();
	}

}
