package paulovareiro24473.options.menuOptions;

import paulovareiro24473.AppGUI;
import paulovareiro24473.Logic;
import paulovareiro24473.STATE;
import paulovareiro24473.files.Config;
import paulovareiro24473.files.Key;
import paulovareiro24473.files.Value;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.enemies.Enemy;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.maps.Map;

public class SaveGameOption extends Option{

	private StoryState story;
	
	public SaveGameOption() {
		super("Save game", "save","sv","svg");
		this.story = (StoryState) AppGUI.getStateBySTATE(STATE.story);
	}

	@Override
	public void execute() {
		if(story == null)
			return;
		
		
		
		String dir = Logic.savesDir + "/"  + StoryState.getPlayer().getName().toLowerCase();
		
		Config maps = new Config(dir, "maps.save");
		Config player = new Config(dir,"player.save");
		
		Player p = StoryState.getPlayer();
		
		//guarda os mapas no ficheiro maps.save//
		for(Map map : Map.availableMaps){
			Key key = new Key(map.getName());
			for(Enemy enemy : map.getEnemiesLayout()){
				key.addValue(new Value(enemy.getName(),Boolean.toString(enemy.isCompleted())));
			}
			maps.addKey(key);
		}
		
		//guarda o jogador no ficheiro player.save//
		player.addKey(p.getName(),new Value[]{
			new Value("" + p.getLevel(),"" + p.getExp(), "" + p.getGold()),
			new Value("" + p.getBaseHP(),"" + p.getBaseMinAtt(),""+ p.getBaseMaxAtt()),
			new Value(p.getHero().toString()),
			new Value("" + p.getHP(), ""+p.getPotions() , ""+p.getMaxPotions(), ""+p.getPotionHeal()),
		});
		
		for(String s : p.getUpgrades()){
			player.addKey(new Key(s));
		}
		
		Logic.printTopHeader("Story Saved");
//		Logic.printHeading("Story was saved successfully");
//		Logic.getch();
		
	}

}
