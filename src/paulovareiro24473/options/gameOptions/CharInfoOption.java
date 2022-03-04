package paulovareiro24473.options.gameOptions;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;

public class CharInfoOption extends Option{

	
	public CharInfoOption() {
		super("Character info", "info", "char","character");
	}

	@Override
	public void execute() {
		String i = StoryState.getPlayer().toString();
		String inf[] = i.split(";");
		String info[] = new String[]{"Name: " + inf[0],
										"HP: " + inf[1] + "/" + inf[2],
										"Level: "+ inf[3],
										"", //<- experiencia a ser modificada no if aseguir
										"Gold: " + inf[5],
										"Attack: "+ inf[6] + "-" + inf[7],
										"Potions: " + inf[8] + "/" + inf[9],
										"Heal p/Potion: " + inf[10]};
		
		if(Integer.valueOf(inf[3]) >= Player.MAXLEVEL){
			info[3] = "Experience: " + inf[4] + "/INFINITE"; 
		}else{
			info[3] = "Experience: " + inf[4] + "/" + Player.expNeeded[Integer.valueOf(inf[3])]; 
		}
		
		Logic.printHeading("Character info","Hero: " + StoryState.getPlayer().getHero().toString());	
		for(String s : info){
			Logic.println(s);
		}
		Logic.separatorln(Logic.lastSeperatorSize);

		
		Logic.getch();
	}

}
