package paulovareiro24473.options.menuOptions;

import paulovareiro24473.AppGUI;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.MenuState;


public class NewStoryOption extends Option{

	private MenuState menu;
	
	public NewStoryOption(MenuState menu) {
		super("New Story","new","story");
		this.menu = menu;
	}

	@Override
	public void execute() {
		AppGUI.currentState = STATE.story;
		
		if(this.name == "New Story"){
			menu.addOption(new SaveGameOption());

			AppGUI.toggleContinue();
		}
	}

}
