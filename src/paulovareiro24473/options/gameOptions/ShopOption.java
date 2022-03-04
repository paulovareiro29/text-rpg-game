package paulovareiro24473.options.gameOptions;

import paulovareiro24473.AppGUI;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;

public class ShopOption extends Option{

	public ShopOption() {
		super("Shop", "shop");
	}

	@Override
	public void execute() {
		AppGUI.currentState = STATE.shop;
		
	}

}
