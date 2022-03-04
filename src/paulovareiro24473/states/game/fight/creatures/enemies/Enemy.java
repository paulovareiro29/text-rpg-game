package paulovareiro24473.states.game.fight.creatures.enemies;

import paulovareiro24473.states.game.fight.creatures.Creature;

public class Enemy extends Creature {

	public static Enemy[] availableEnemies =new Enemy[]{};
	
	protected int exp, gold;
	protected boolean completed;

	public Enemy(String name, int maxHP, int level, int minAtt, int maxAtt, int exp, int gold) {
		super(name, maxHP, level, minAtt, maxAtt);
		this.exp = exp;
		this.gold = gold;
		this.completed = false;
	}

	public Enemy(String name, int maxHP, int level, int minAtt, int maxAtt, int exp, int gold, boolean completed) {
		super(name, maxHP, level, minAtt, maxAtt);
		this.exp = exp;
		this.gold = gold;
		this.completed = completed;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Enemy clone() {
		return new Enemy(this.name, this.maxHP, this.level, this.minAtt, this.maxAtt, this.exp, this.gold, this.completed);

	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean killed) {
		this.completed = killed;
	}

 
		public static void addEnemy(Enemy enemy){ //adicionar inimigo ao array de availableEnemies
			Enemy[] newEnemies = new Enemy[availableEnemies.length + 1 ];
			for(int i = 0; i < availableEnemies.length; i++){
				newEnemies[i] = availableEnemies[i];
			}
			newEnemies[newEnemies.length -1] = enemy;
			availableEnemies = newEnemies;
		}
	
}
