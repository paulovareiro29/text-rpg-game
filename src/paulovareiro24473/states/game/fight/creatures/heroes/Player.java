package paulovareiro24473.states.game.fight.creatures.heroes;

import paulovareiro24473.Logic;
import paulovareiro24473.states.game.fight.creatures.Creature;

public class Player extends Creature{

	protected int potions, maxPotions,potionHeal;
	protected int exp, gold;
	protected int baseHP,baseMinAtt,baseMaxAtt;
	
	protected HERO hero;
	
	public static int[] expNeeded;
	
	public static int MAXLEVEL;
	public static double DEFAULT_STAT_GROWTH_MULTIPLIER;
	public static int DEFAULT_STAT_GROWTH_RATE;
	public static double DEFAULT_EXP_NEED_MULTIPLIER;
	public static int DEFAULT_POTIONS, DEFAULT_POTION_HEAL;
	
	public String upgrades[] = new String[]{};
	
	public Player(String name,int maxHP, int minAtt, int maxAtt, HERO hero) {
		super(name, maxHP, 1, minAtt, maxAtt);
		this.baseHP = maxHP;
		this.baseMinAtt = minAtt;
		this.baseMaxAtt = maxAtt;
		this.hero = hero;
		
		this.potionHeal = DEFAULT_POTION_HEAL;
		this.maxPotions = DEFAULT_POTIONS;
		this.potions = maxPotions;
		exp = 0;
		gold = 0;
	
		refreshStats();
		heal(this.maxHP);
	}
	
	public void addUpgrade(String s){
		String[] tempUpgrades = new String[upgrades.length+1];
		for(int i = 0; i < upgrades.length; i++){
			tempUpgrades[i] = upgrades[i];
		}
		tempUpgrades[tempUpgrades.length-1] = s;
		upgrades = tempUpgrades;
	}
	
	public void subtract(int gold){
		this.gold -= gold;
	}
	
	public void receiveGold(int gold){
		this.gold += gold;
	}

	public void receiveEXP(int exp){
		setExp(getExp() + exp);

		if(isLevelUp()){
			levelUp();
			
			Logic.cls();
			Logic.printHeading("You have leveled up!!", "Your level: " + level);
			Logic.getch();
		}
	}
	
	public void levelUp(int level){
		for(int i = 0; i < level-1; i++){
			levelUp();
		}
	}
	
	public void levelUp(){
		level++;
		exp = 0;
		if(level >= Player.MAXLEVEL){
			Logic.printHeading("You have reached maximum level!!");
			Logic.getch();
		}
		refreshStats();
		heal(maxHP);
		
	}
	
	protected void refreshStats(){
		maxHP += calcStat(baseHP);
		minAtt += calcStat(baseMinAtt);
		maxAtt += calcStat(baseMaxAtt);
	}
	
	private int calcStat(int base){
		return (int) (base * DEFAULT_STAT_GROWTH_MULTIPLIER) * level/DEFAULT_STAT_GROWTH_RATE ;
	}
	
	
	
	private boolean isLevelUp(){
		if(level >= expNeeded.length)
			return false;
		
		if(exp >= expNeeded[level])
			return true;
		
		return false;	
	}
	
	public boolean usePotion(){
		if(getPotions()==0)
			return false;
		if(HP == maxHP)
			return false;
		heal(potionHeal);
		setPotions(getPotions()-1);
		
		return true;
	}
	
	public String toString(){
		String string = name + ";";
		string += HP + ";"; 
		string += maxHP + ";";
		string += level + ";";
		string += exp +";";
		string += gold + ";";
		string += minAtt + ";";
		string += maxAtt + ";";
		string += potions + ";";
		string += maxPotions + ";";
		string += potionHeal + ";";
		return string;
	}


	public int getPotions() {
		return potions;
	}


	public void setPotions(int potions) {
		this.potions = potions;
	}


	public int getMaxPotions() {
		return maxPotions;
	}


	public void setMaxPotions(int maxPotions) {
		this.maxPotions = maxPotions;
	}

	public int getPotionHeal() {
		return potionHeal;
	}

	public void setPotionHeal(int potionHeal) {
		this.potionHeal = potionHeal;
	}

	public int getExp() {
		return exp;
	}

	public String getExpNeededNextLevel(){
		if(level >= MAXLEVEL){
			return "INF";
		}
		return "" + expNeeded[level];
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}

	public HERO getHero() {
		return hero;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public int getBaseMinAtt() {
		return baseMinAtt;
	}

	public int getBaseMaxAtt() {
		return baseMaxAtt;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String[] getUpgrades() {
		return upgrades;
	}

}
