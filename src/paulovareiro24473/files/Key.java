package paulovareiro24473.files;

public class Key {

	private String name;
	private Value[] values;
	
	public Key(String name){
		this.name = name;
		values = new Value[]{};
	}
	
	public void addValue(Value value){
		Value[] tempVals = new Value[values.length + 1];
		for(int i = 0; i < values.length; i++){
			tempVals[i] = values[i];
		}
		tempVals[tempVals.length-1] = value;
		values = tempVals;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Value[] getValues() {
		return values;
	}

	public void setValues(Value[] values) {
		this.values = values;
	}
	
}
