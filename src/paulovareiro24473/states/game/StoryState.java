package paulovareiro24473.states.game;

import paulovareiro24473.Logic;
import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.gameOptions.BackMenuOption;
import paulovareiro24473.options.gameOptions.CharInfoOption;
import paulovareiro24473.options.gameOptions.FightOption;
import paulovareiro24473.options.gameOptions.ShopOption;
import paulovareiro24473.states.State;
import paulovareiro24473.states.game.fight.creatures.heroes.Assassin;
import paulovareiro24473.states.game.fight.creatures.heroes.HERO;
import paulovareiro24473.states.game.fight.creatures.heroes.Mage;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.creatures.heroes.Warrior;


public class StoryState extends State{
		
	private static Player player;
	
	public static Player[] availableClasses = new Player[]{new Warrior(""),
															new Assassin(""), 
															new Mage("")};
	
	public StoryState() {
		super("Story",STATE.story);
		this.options = new Option[]{new FightOption(), 
									new CharInfoOption(),
									new ShopOption(),
									new BackMenuOption()};
	}

	@Override
	public void run() {
		if(isNewGame()){
			newPlayer();
		}
		
		//---------
		Logic.drawOptions(options, "Story Mode");
		Logic.chooseOption(options);
		//---------			
	}
	
	private void newPlayer(){
		String Pname;
		
		do{
			Logic.printHeading("New Character");
			Pname = Logic.read("Name: ");
			Logic.cls();
			Pname = Pname.trim();
			if(Pname.length() == 0){
				Logic.printHeading("Minimum length: 0");
				Logic.getch();
				Logic.cls();
			}
			
			if(Pname.length() > 15){
				Logic.printHeading("Maximum length: 8");
				Logic.getch();
				Logic.cls();
			}
		}while(Pname.length() == 0 || Pname.length() > 8);
		
		String classe;
		while(true){
			Logic.printHeading("New Character");
			Logic.println("Name: " + Pname);
			
			Logic.println("Choose your class!");
			for(int i = 0; i < availableClasses.length; i++){
				Logic.println( "[" + (i+1) + "] - " + availableClasses[i].getHero().toString());
			}
			
			classe = Logic.read("Class: ");
			Logic.cls();
			try{
				int opc = Integer.parseInt(classe);
				if(opc-1 >= 0 && opc-1 < availableClasses.length){
					player = availableClasses[opc-1];
					player.setName(Pname);
					return;
				}
			}catch(NumberFormatException e){
				for(int i = 0; i < availableClasses.length; i++){
					if(classe == availableClasses[i].getHero().toString()){
						player = availableClasses[i];
						player.setName(Pname);
						return;
					}
				}
			}
		}
	}

	public boolean isNewGame(){
		if(player == null)
			return true;
		return false;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		StoryState.player = player;
	}
	
}
