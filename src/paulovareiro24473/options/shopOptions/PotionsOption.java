package paulovareiro24473.options.shopOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.options.shopOptions.options.potions.HealOption;
import paulovareiro24473.options.shopOptions.options.potions.RefillPotionsOption;
import paulovareiro24473.states.game.StoryState;

public class PotionsOption extends Option{

	public static Upgrade[] availableUpgrades = new Upgrade[]{
			new RefillPotionsOption(),
			new HealOption()
	};
	
	private Upgrade[] upgrades = new Upgrade[]{};
	
	public PotionsOption() {
		super("Potions", "refs");
	}

	@Override
	public void execute() {
		loadUpgrades();
		if(upgrades.length == 0){ //se nao houver upgrades na loja
			Logic.printHeading("Refills is empty");
			Logic.getch();
			return;
		}
		
		//---------Escolher opçoes---------//
		Logic.drawOptions(upgrades, "Shop -> Potions");
		Logic.chooseOption(upgrades);
		//---------------------------------//
	}
	
	private	void loadUpgrades(){
		upgrades = new Upgrade[]{};
		for(Upgrade u : availableUpgrades){
			if(u.getAvailableFrom() <= StoryState.getPlayer().getLevel() && //se tiver nivel igual ou maior que o nivel do upgrade
					(u.getUses() > 0  || u.getUses() == -1)){ //e poder se usado ainda
				addUpgrade(u); //adiciona o upgrade ao array dos upgrades que o utilizador pode usar
			}
		}
	}
	
	private void addUpgrade(Upgrade upgrade){
		Upgrade[] newUpgrades = new Upgrade[upgrades.length + 1];
		for(int i = 0; i < upgrades.length; i++){
			newUpgrades[i] = upgrades[i];
		}
		newUpgrades[newUpgrades.length -1] = upgrade;
		upgrades = newUpgrades;
	}
	

}
