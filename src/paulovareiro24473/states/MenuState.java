package paulovareiro24473.states;

import paulovareiro24473.Logic;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.menuOptions.LoadStoryOption;
import paulovareiro24473.options.menuOptions.NewStoryOption;
import paulovareiro24473.options.menuOptions.RankingOption;

public class MenuState extends State{
	
	public MenuState() {
		super("Menu",STATE.menu);
		this.options = new Option[] {new NewStoryOption(this),
										new RankingOption(),
										new LoadStoryOption(this)};
	}

	@Override
	public void run() {
		Logic.drawOptions(options, "Menu");
		Logic.chooseOption(options);
	}

	
	

}
