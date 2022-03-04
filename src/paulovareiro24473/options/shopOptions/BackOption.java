package paulovareiro24473.options.shopOptions;

import paulovareiro24473.AppGUI;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;

public class BackOption extends Option{

	public BackOption() {
		super("Leave Shop", "leave");
	}

	@Override
	public void execute() {
		AppGUI.currentState = STATE.story;
	}

}
