package paulovareiro24473;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import paulovareiro24473.files.Config;
import paulovareiro24473.files.Key;
import paulovareiro24473.files.Value;
import paulovareiro24473.options.Option;
import paulovareiro24473.states.game.fight.creatures.enemies.Enemy;
import paulovareiro24473.states.game.fight.creatures.heroes.Player;
import paulovareiro24473.states.game.fight.maps.Map;

public class Logic {

//	public static Scanner input = new Scanner(System.in);
	public static String input = "";
	public static int lastSeperatorSize;

	// -------------dirs---------------
	public static String gameDir = "./game";
	public static String savesDir = gameDir + "/saves";
	// --------------files---------------
	private static Config config;

	//---------------variaveis------------
	public static String TAB = "        ";
	public static boolean getch = false;
	
	
	public static void drawOptions(Option[] options, String heading) {
		printHeading(heading);
		for (int i = 0; i < options.length; i++) {
			println("[" + (i + 1) + "] " + options[i].getName());
		}
		separatorln(Logic.lastSeperatorSize);
	}

	public static void drawOptions(Option[] options) {
		String text = "";
		for (int i = 0; i < options.length; i++) {
			text += "[" + (i + 1) + "] " + options[i].getName() + "  ";
		}
		printHeading(text);
	}

	public static void listFiles(File folder){
		for(File f : folder.listFiles()){
			println(" - " + f.getName());
		}
	}
	
	public static void loadMap(String mapName, Config config) throws IOException{
		Key map = config.getKeyByName(mapName);
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		
		for(Value val : map.getValues()){ //para cada valor, ou seja monstro de cada mapa
			String[] mob = val.toString().split(",");  //separar o valor em dois
			boolean found = false;
			for(Enemy e : Enemy.availableEnemies){  //ver qual é o monstro 
				if(mob[0].equals(e.getName())){ //se tiverem o mesmo nome
					found = true;
					Enemy clone = e.clone(); //clona o monstro

					clone.setCompleted((mob[1].equals("true")) ? true : false); //poe o estado do inimigo : completado ou nao
					
					enemies.add(clone); //adiciona ao array dos inimigos
				}
			
			}
			
			if(!found)
				throw new IOException("Error trying to load " + mapName + " - Enemy \"" + mob[0] + "\" not found ");
		}

		Map.addMap(new Map(mapName, new String[]{}, enemies));
	}
	
	public static void loadFromDefaultMaps() throws IOException{
		Config config = new Config(gameDir,"maps");
		if(config.isEmpty()){
			Key m = new Key("The forest");
			Key t = new Key("The desert");
			Key d = new Key("The cave");
			m.setValues(new Value[]{new Value("Mushroom,false"),new Value("Mushroom,false"),new Value("Mushroom,false"),new Value("Mushroom,false"),new Value("Mushroom,false"),new Value("Mushroom,false")});
			t.setValues(new Value[]{new Value("Tree,false"),new Value("Tree,false"),new Value("Tree,false"),new Value("Tree,false"),new Value("Tree,false"),new Value("Tree,false")});
			d.setValues(new Value[]{new Value("Dog,false"),new Value("Dog,false"),new Value("Dog,false"),new Value("Dog,false"),new Value("Dog,false"),new Value("Dog,false")});
			config.addKey(m);
			config.addKey(t);
			config.addKey(d);
		}
		Map.availableMaps = new Map[]{};
		for(Key k : config.getKeys()){
			loadMap(k.getName(),config);
		}
	}
	
	
	public static void loadEnemies() throws IndexOutOfBoundsException{
		Config config = new Config(gameDir,"enemies");
		if(config.isEmpty()){ //se o ficheiro enemies estiver vazio adiciona estes por default
			Key m = new Key("Mushroom");
			Key t = new Key("Tree");
			Key d = new Key("Dog");
			m.setValues(new Value[]{new Value("50"),new Value("1"),new Value("1"),new Value("5"),new Value("10"),new Value("10")});
			t.setValues(new Value[]{new Value("100"),new Value("2"),new Value("5"),new Value("10"),new Value("15"),new Value("15")});
			d.setValues(new Value[]{new Value("150"),new Value("3"),new Value("10"),new Value("15"),new Value("20"),new Value("20")});
			config.addKey(m);
			config.addKey(t);
			config.addKey(d);
		}
		for(Key k : config.getKeys()){
			Value[] v = k.getValues();

			Enemy.addEnemy(new Enemy(k.getName(),
					Integer.valueOf(v[0].toString()),
					Integer.valueOf(v[1].toString()), 
					Integer.valueOf(v[2].toString()), 
					Integer.valueOf(v[3].toString()), 
					Integer.valueOf(v[4].toString()), 
					Integer.valueOf(v[5].toString())));
		}
	}
	
	public static void loadFiles() {
		loadConfig(); //carrega a configuraçao do jogo
		
		File saveFolder = new File(gameDir,"/saves");
		if(!saveFolder.exists()){
			saveFolder.mkdir();
		}
		try {
			loadEnemies();//carrega os inimigos do ficheiro enemies
			loadFromDefaultMaps(); //carrega os mapas do ficheiro maps
		}catch(IndexOutOfBoundsException e){
			printHeading("Could not load enemies files","Delete the file to fix");
			getch();
			AppGUI.quit();
		}catch(IOException e){
			printHeading(e.getMessage());
			getch();
			AppGUI.quit();
		}
		
		if (!updateDefaults()) {
			cls();
			printHeading("Unable to load configuration correctly", "Game is now quitting");
			getch();
			AppGUI.quit();
		}

	}



	
	
	private static void loadConfig() {
		config = new Config(gameDir, "config");
		config.addKey("MAXLEVEL",new Value[]{new Value("100")});
		config.addKey("DEFAULT_EXP_NEED_MULTIPLIER", new Value[]{new Value("0.1")});
		config.addKey("DEFAULT_STAT_GROWTH_MULTIPLIER", new Value[]{new Value("1.95")});
		config.addKey("DEFAULT_STAT_GROWTH_RATE", new Value[]{new Value("40")});
		config.addKey("DEFAULT_POTIONS", new Value[]{new Value("3")});
		config.addKey("DEFAULT_POTION_HEAL", new Value[]{new Value("20")});
	}

	private static boolean updateDefaults() {
		try {
			Player.MAXLEVEL = Integer.valueOf(config.getKeyByName("MAXLEVEL").getValues()[0].getValue()[0]);
			Player.DEFAULT_STAT_GROWTH_MULTIPLIER = Double
					.valueOf(config.getKeyByName("DEFAULT_STAT_GROWTH_MULTIPLIER").getValues()[0].getValue()[0]);
			
			Player.DEFAULT_STAT_GROWTH_RATE = Integer.valueOf(config.getKeyByName("DEFAULT_STAT_GROWTH_RATE").getValues()[0].getValue()[0]);
			
			Player.DEFAULT_POTIONS = Integer.valueOf(config.getKeyByName("DEFAULT_POTIONS").getValues()[0].getValue()[0]);
			
			Player.DEFAULT_POTION_HEAL = Integer.valueOf(config.getKeyByName("DEFAULT_POTION_HEAL").getValues()[0].getValue()[0]);
			
			Player.DEFAULT_EXP_NEED_MULTIPLIER = Double.valueOf(config.getKeyByName("DEFAULT_EXP_NEED_MULTIPLIER").getValues()[0].getValue()[0]);
			

			int baseEXP = 100;
			int[] expNeeded = new int[Player.MAXLEVEL];
			for (int i = 2; i <= Player.MAXLEVEL; i++) {
				baseEXP = (int) Math.floor(baseEXP + (baseEXP * Player.DEFAULT_EXP_NEED_MULTIPLIER));
				expNeeded[i - 1] = baseEXP;
			}

			Player.expNeeded = expNeeded;
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void chooseOption(Option[] options) {
		String opc = "";
		opc = read("Option: "); // espera por input do utilizador
		cls();
		try {
			int Nopc = Integer.valueOf(opc);
			if (Nopc < options.length + 1 && Nopc > 0) {
				options[Nopc - 1].execute();

			}
		} catch (NumberFormatException e) {
			for (Option option : options) {
				if (opc.equalsIgnoreCase(option.getName())) {
					option.execute();

					return;
				} else if (option.checkAlias(opc)) {
					option.execute();

					return;
				}
			}
		}

	}

	public static void getch() {
		read("Press enter to continue");
		cls();
	}

	public static void printTopHeader(String text){
		AppGUI.topHeader.setText(text);

		current = System.currentTimeMillis();
		wasWritten = true;
	}
	
	static long current;
	static boolean wasWritten;
	
	public static void println(String text){
		AppGUI.textArea.append(text + "\n");
		refreshCursorPosition();
	}
	
	public static void print(String text){
		AppGUI.textArea.append(text);
		refreshCursorPosition();
	}
	
	public static void refreshCursorPosition(){
		AppGUI.textArea.setCaretPosition(AppGUI.textArea.getDocument().getLength()-1);
	}
	
	public static String read(String text) {
		AppGUI.textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), text, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
			
		while(input == null){
			System.out.println("Waiting for input");
			if(System.currentTimeMillis() - current > 3000 && wasWritten){
				AppGUI.topHeader.setText("");
				wasWritten = false;
			}
			refreshCursorPosition();
		}
		String r = input;
		input = null;
		return r;
	}
	
	public static int random(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	public static void cls() {
		AppGUI.textArea.setText("");
		for(int i = 0; i < 50;i++){
			println("\n");
		}
		refreshCursorPosition();
	}

	public static void separator(int n) {
		lastSeperatorSize = n;
		for (int i = 0; i < n; i++) {
			print("-");
		}
	}

	public static void separatorln(int n) {
		lastSeperatorSize = n;
		for (int i = 0; i < n; i++) {
			print("-");
		}
		print("\n");
	}

	public static void printHeading(String heading1, String heading2) {
		int larger = (heading1.length() > heading2.length()) ? heading1.length() : heading2.length();

		separatorln(larger + 8 * 2);
		println(TAB + heading1);
		println(TAB + heading2);
		separatorln(larger + 8 * 2);
		
	}

	public static void printHeading(String heading) {
		separatorln(heading.length() + 8 * 2);
		println(TAB + heading);
		separatorln(heading.length() + 8 * 2);
	}
}
