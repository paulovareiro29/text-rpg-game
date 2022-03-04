package paulovareiro24473.options.gameOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.fight.maps.Map;

public class FightOption extends Option{
	
	
	public FightOption() {
		super("Fight");
	}

	@Override
	public void execute() {
		Logic.drawOptions(Map.getMaps(), "Choose map");
		Logic.chooseOption(Map.getMaps());
		
//		Logic.getch();
	}

}
