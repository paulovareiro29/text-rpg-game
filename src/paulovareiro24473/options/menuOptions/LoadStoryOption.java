package paulovareiro24473.options.menuOptions;

import java.io.File;
import java.io.IOException;

import paulovareiro24473.AppGUI;
import paulovareiro24473.Logic;
import paulovareiro24473.files.Config;
import paulovareiro24473.files.Key;
import paulovareiro24473.files.Value;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.MenuState;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.heroes.HERO;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.maps.Map;

public class LoadStoryOption extends Option{

	protected MenuState menu;
	
	public LoadStoryOption(MenuState menu) {
		super("Load Game", "load","lg");
		this.menu = menu;
	}

	@Override
	public void execute() {
		File folder = new File(Logic.savesDir);
		
		
		if(folder.listFiles().length == 0){
			Logic.printHeading("Saves folder is empty");
			Logic.getch();
			Logic.cls();
			return;
		}
		Logic.printHeading("Load Story");
		Logic.listFiles(folder);
		Logic.separatorln(Logic.lastSeperatorSize);
		
		String choosen = Logic.read("Folder: ").toLowerCase();

			for(File f : folder.listFiles()){
				if(f.getName().equals(choosen)){
					String path = Logic.savesDir + "/" + choosen;
					folder = new File(path);
					Logic.cls();
					if((new File(path,"player.save").exists())){ //se encontrar o ficheiro player.save vai carregar o jogador
						Config pConfig = new Config(path,"player.save");
						Key playerinfo = pConfig.getKeys().get(0);
						
						Value[] v = playerinfo.getValues();
	
						Player tempPlayer = new Player(playerinfo.getName(),
											Integer.valueOf(v[1].getValue()[0]), //basehp
											Integer.valueOf(v[1].getValue()[1]), //baseMinAtt
											Integer.valueOf(v[1].getValue()[2]), //baseMaxAtt
											HERO.valueOf(v[2].getValue()[0]));
						
						tempPlayer.levelUp(Integer.valueOf(v[0].getValue()[0])); //level
						tempPlayer.setExp(Integer.valueOf(v[0].getValue()[1])); //exp
						tempPlayer.setGold(Integer.valueOf(v[0].getValue()[2])); //gold
						tempPlayer.setHP(Integer.valueOf(v[3].getValue()[0])); //current hp
						tempPlayer.setPotions(Integer.valueOf(v[3].getValue()[1])); //current potions
						tempPlayer.setMaxPotions(Integer.valueOf(v[3].getValue()[2])); //max potions
						tempPlayer.setPotionHeal(Integer.valueOf(v[3].getValue()[3])); //potion heal
						
						//upgrades
						for(int i = 1; i<pConfig.getKeys().size();i++){
							tempPlayer.addUpgrade(pConfig.getKeys().get(i).getName());
						}
						
						StoryState.setPlayer(tempPlayer); //atualiza o jogador
						
						
						try{
						if((new File(path,"maps.save").exists())){ //se o ficheiro maps.save existir no folder
							Config mConfig = new Config(path,"maps.save");
							Map.availableMaps = new Map[]{};
							for(Key k : mConfig.getKeys()){
								Logic.loadMap(k.getName(),mConfig); //carrega todos os mapas
							}
						}else{ 
							Logic.printHeading(">> Maps.save file was not found <<"); 
							Logic.getch();
							Logic.loadFromDefaultMaps(); //senao carregar o maps.save, carrega os mapas default, ou seja, com tudo incompleto ainda
						}
						}catch(IOException e){
							Logic.printHeading(e.getMessage());
							Logic.getch();
							AppGUI.quit();
						}
	
						Logic.printHeading(">> Story was successfully loaded <<");
						Logic.getch();
	
						menu.getOptions()[0].execute();
	//					Launcher.toggleContinue(); //muda o nome da opçao "new story" para "continue story"
	//					Launcher.currentState = STATE.story; //muda o estado corrente
					}else{ //se nao encontrar o player.save, vai dizer que a historia nao pode ser carregada
						Logic.printHeading(">> Story could not be loaded <<");
						Logic.getch();
					}	
				}
			}

		
		Logic.cls();
	}
}
