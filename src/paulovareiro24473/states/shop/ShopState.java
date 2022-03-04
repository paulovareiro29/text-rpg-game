package paulovareiro24473.states.shop;


import paulovareiro24473.Logic;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.shopOptions.BackOption;
import paulovareiro24473.options.shopOptions.PotionsOption;
import paulovareiro24473.options.shopOptions.UpgradesOption;
import paulovareiro24473.states.State;

public class ShopState extends State{


	public ShopState() {
		super("Shop", STATE.shop);
		this.options = new Option[]{new PotionsOption(),new UpgradesOption(),new BackOption()};
	}

	@Override
	public void run() {		
		//---------Escolher opçoes---------//
		Logic.drawOptions(options, "Shop");
		Logic.chooseOption(options);
		//---------------------------------//
	}


}
