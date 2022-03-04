package paulovareiro24473.options.gameOptions;

import paulovareiro24473.AppGUI;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;

public class BackMenuOption extends Option{
	
	public BackMenuOption() {
		super("Back to menu","menu","back");
	}

	@Override
	public void execute() {
		AppGUI.currentState = STATE.menu;
	}

}
