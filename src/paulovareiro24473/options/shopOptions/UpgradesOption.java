package paulovareiro24473.options.shopOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.shopOptions.options.Upgrade;
import paulovareiro24473.options.shopOptions.options.upgrades.MaxPotionUpgrade;
import paulovareiro24473.options.shopOptions.options.upgrades.PotionHealUpgrade;
import paulovareiro24473.states.game.StoryState;

public class UpgradesOption extends Option{

	public static Upgrade[] availableUpgrades = new Upgrade[]{
			new MaxPotionUpgrade("Maximum potions +1 [lv",5,50), new PotionHealUpgrade("Potion heal x3 [lv",5,50),
			new MaxPotionUpgrade("Maximum potions +1 [lv",25,50),new PotionHealUpgrade("Potion heal x3 [lv",25,50),
			new MaxPotionUpgrade("Maximum potions +1 [lv",40,50),new PotionHealUpgrade("Potion heal x3 [lv",40,50),
			new MaxPotionUpgrade("Maximum potions +1 [lv",60,50),new PotionHealUpgrade("Potion heal x3 [lv",60,50),
			new MaxPotionUpgrade("Maximum potions +1 [lv",80,50),new PotionHealUpgrade("Potion heal x3 [lv",80,50)			
	};
	
	private Upgrade[] upgrades = new Upgrade[]{};
	
	public UpgradesOption() {
		super("Upgrades","upgr","upg","grades");
	}

	@Override
	public void execute() {
		loadUpgrades();
		if(upgrades.length == 0){ //se nao houver upgrades na loja
			Logic.printHeading("Upgrades is empty");
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
				Boolean found = false;
				for(String s : StoryState.getPlayer().getUpgrades()){ //loop por todos os upgrades que o jogador tem 
					if(u.getName().equals(s)){ //se o nome for o mesmo, nao mostrar na loja
						found = true;
						break;
					}
				}
				if(!found)
					addUpgrade(u);//adiciona o upgrade ao array dos upgrades que o utilizador pode usar
			}	
		}
		sortUpgradesByLevel();
	}
	
	private void addUpgrade(Upgrade upgrade){
		Upgrade[] newUpgrades = new Upgrade[upgrades.length + 1];
		for(int i = 0; i < upgrades.length; i++){
			newUpgrades[i] = upgrades[i];
		}
		newUpgrades[newUpgrades.length -1] = upgrade;
		upgrades = newUpgrades;
	}
	
	private void sortUpgradesByLevel(){
		Upgrade temp;
		for(int i = 0; i < upgrades.length; i++){ //percorre o array todo
			for(int j = 1; j < upgrades.length - i; j++){ //em cada iteraçao , percorre outra vez o array todo 
				if(upgrades[j - 1].getAvailableFrom() > upgrades[j].getAvailableFrom()){ 
					//se o numero atual for maior que o numero anterior vai fazer uma troca de esses valores. Ao fim de toda a iteraçao, o array vai estar todo ordenado
					temp = upgrades[j-1];
					upgrades[j-1] = upgrades[j];
					upgrades[j] = temp;
				}
			}
		}
	}

}
