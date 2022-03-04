package paulovareiro24473.options.shopOptions.options;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;

public abstract class Upgrade extends Option{

	protected int uses,availableFrom,cost;

	public Upgrade(String name, String[] alias,int uses,int availableFrom,int cost) {
		super(name, alias);
		this.name = this.name + " :" + cost + "g";
		this.uses = uses;
		this.availableFrom = availableFrom;
		this.cost = cost;
	}

	@Override
	public void execute() {
		Player p = StoryState.getPlayer();
		if(p.getGold() >= cost){
			upgrade(); //chama o  upgrade
			p.subtract(cost); //tira o dinheiro do jogador
			if(uses != -1 || uses != 0){
				uses--;
			}
			p.addUpgrade(name);
		}else{
			Logic.printHeading("You dont have enough gold");
		}
		
	}
	
	public abstract void upgrade();
	
	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}

	public int getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(int availableFrom) {
		this.availableFrom = availableFrom;
	}


}
