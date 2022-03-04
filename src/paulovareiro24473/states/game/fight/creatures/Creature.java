package paulovareiro24473.states.game.fight.creatures;

import paulovareiro24473.Logic;

public abstract class Creature{
	
	protected String name;
	protected int HP, maxHP, level, minAtt, maxAtt;
	protected int lastDamageGiven, lastDamageReceived;
	

	
	public Creature(String name, int maxHP, int level, int minAtt, int maxAtt){
		this.name = name;
		this.HP = maxHP;
		this.maxHP = maxHP;
		this.level = level;
		this.minAtt = minAtt;
		this.maxAtt = maxAtt;
		this.lastDamageGiven = 0;
		this.lastDamageReceived = 0;
		
	}

	public void heal(int health){
		HP += health;
		if(HP > maxHP){
			HP = maxHP;
		}
	}
	
	public void heal(){
		HP = maxHP;
	}
	
	public void damage(int damage){
		Logic.println(this.name + " has received " + damage + " damage!");
		HP -= damage;
		lastDamageReceived = damage;
	}
	
	public boolean isDead(){
		return HP <= 0 ;
	}
	
	public void attack(Creature target){
		Logic.println(this.name + " has attacked " + target.getName());
		int damage = Logic.random(minAtt, maxAtt);
		target.damage(damage);
		lastDamageGiven = damage;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public int getMinAtt() {
		return minAtt;
	}

	public void setMinAtt(int minAtt) {
		this.minAtt = minAtt;
	}

	public int getMaxAtt() {
		return maxAtt;
	}

	public void setMaxAtt(int maxAtt) {
		this.maxAtt = maxAtt;
	}




	public int getLastDamageGiven() {
		return lastDamageGiven;
	}




	public int getLastDamageReceived() {
		return lastDamageReceived;
	}
	
	
}
