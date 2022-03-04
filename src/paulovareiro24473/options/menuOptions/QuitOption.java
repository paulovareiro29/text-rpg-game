package paulovareiro24473.options.menuOptions;


import paulovareiro24473.AppGUI;
import paulovareiro24473.options.Option;

public class QuitOption extends Option{

	public QuitOption() {
		super("Quit", "sair");
	}

	@Override
	public void execute() {
		AppGUI.quit();	
	}

}
