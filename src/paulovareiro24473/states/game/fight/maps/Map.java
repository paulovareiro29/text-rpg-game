package paulovareiro24473.states.game.fight.maps;


import java.util.ArrayList;

import paulovareiro24473.Logic;
import paulovareiro24473.options.Option;
import paulovareiro24473.options.mapOptions.AttackOption;
import paulovareiro24473.options.mapOptions.HealOption;
import paulovareiro24473.options.mapOptions.RunOption;
import paulovareiro24473.states.game.StoryState;
import paulovareiro24473.states.game.fight.creatures.enemies.Enemy;


public class Map extends Option{
	
	
	public static Map[] availableMaps = new Map[]{ };
	
	
	protected int level;
	protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	protected ArrayList<Enemy> enemiesLayout = new ArrayList<Enemy>();
	protected Boolean[] completedEnemies;
	
	private boolean corage = true;

	private Option[] options = new Option[]{new AttackOption(this),new HealOption(),new RunOption(this)};
	
	public Map(String name, String[] alias, ArrayList<Enemy>enemiesLayout) {
		super(name,alias);
		this.enemiesLayout = enemiesLayout;
		
		reset();
	}
	
	
	
	@Override
	public void execute() {    

		for(int i = 0 ; i <availableMaps.length; i++){ //percorre todos os mapas para verificar se o anterior ja foi feito
			if(availableMaps[i].equals(this)){
				if(i == 0) //Se este mapa for o primeiro mapa entra no mapa
					break;
				if(!availableMaps[i-1].isCompleted()){ //se o mapa anterior nao foi completado
					Logic.printHeading(">> You have to defeat the previous maps <<"); //print a mensagem 
					Logic.getch();
					Logic.cls();
					return; //sai do execute do mapa
				}else{
					break;
				}
			}
		}
	
		
		//quando entra no mapa
		reset(); //dá load dos inimigos
		Logic.printHeading(name);
		Logic.println("You have entered " + name + "!!");
		Logic.separatorln(Logic.lastSeperatorSize);
		Logic.getch();
		
		
		corage = true; //este corage é para a opçao  de fugir
		while(corage){
			Logic.cls();
			
			printStats();
			
			Logic.drawOptions(options);
			Logic.chooseOption(options);
		}

	}
	
	private void printStats(){
		String playerHP = StoryState.getPlayer().getName() + " HP: " + StoryState.getPlayer().getHP();

		String[] stColumn = new String[]{"Exp: " + StoryState.getPlayer().getExp() + "/" + StoryState.getPlayer().getExpNeededNextLevel(),
										playerHP};
		
		String[] ndColumn = new String[]{"|Enemy:"  + enemies.size() + " out of " + enemiesLayout.size(),
											"|"+enemies.get(0).getName() + " HP: " + enemies.get(0).getHP()};
		
		int biggest = (stColumn[0].length() > stColumn[1].length()) ? stColumn[0].length() : stColumn[1].length() ;

		Logic.println(stColumn[0] + getSpacesLeft(biggest,stColumn[0]) + ndColumn[0]);
		Logic.println(stColumn[1] + getSpacesLeft(biggest,stColumn[1]) + ndColumn[1]);
		
	}
	
	private String getSpacesLeft(int biggest,String string){
		String spaces = "";
		int amount = (biggest + (24 - (biggest % 8)) -  string.length());

		for(int i = 0; i < amount; i++){
			spaces += " ";
		}
		return spaces;
	}
	
	public void reset(){
		this.enemies = new ArrayList<Enemy>();
		for(Enemy enemy : enemiesLayout){
			enemies.add(enemy.clone());
		}
	}
	
	public void listEnemies(){ //era usado para debug
		for(int i = 0; i < enemies.size(); i++){
			Logic.println("[" + i + "] " + enemies.get(i).getName());
		}
	}
	
	public static ArrayList<Enemy> genEnemies(Enemy enemy, int quantity){ //usado para gerar X quantidade de um certo inimigo
		ArrayList<Enemy> array = new ArrayList<Enemy>();
		for(int i = 0; i < quantity; i++){
			array.add(enemy.clone());
		}
		return array;
	}
	
	public static ArrayList<Enemy> genEnemies(String enemy, int quantity){
		Enemy e = null;
		for(Enemy ene : Enemy.availableEnemies){
			if(ene.getName() == enemy){
				e = ene;
			}
		}
		
		if(e == null)
			return null;
			
		ArrayList<Enemy> array = new ArrayList<Enemy>();
		for(int i = 0; i < quantity; i++){
			array.add(e.clone());
		}
		return array;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}


	public boolean isCorage() {
		return corage;
	}


	public void setCorage(boolean corage) {
		this.corage = corage;
	}

	public ArrayList<Enemy> getEnemiesLayout() {
		return enemiesLayout;
	}


	public void setEnemiesLayout(ArrayList<Enemy> enemiesLayout) {
		this.enemiesLayout = enemiesLayout;
	}


	public boolean isCompleted() {
		for(Enemy enemy : enemiesLayout){
			if(!enemy.isCompleted()){
				return false;
			}
		}
		return true;
	}

	public static Map[] getMaps() {
		return availableMaps;
	}
	
	public static void addMap(Map map){
		Map[] newMaps = new Map[availableMaps.length + 1 ];
		for(int i = 0; i < availableMaps.length; i++){
			newMaps[i] = availableMaps[i];
		}
		newMaps[newMaps.length -1] = map;
		availableMaps = newMaps;
	}

	
}
