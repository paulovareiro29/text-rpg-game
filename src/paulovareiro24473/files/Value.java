package paulovareiro24473.files;

public class Value {

	private String[] value;
	
	public Value(String... value){
		this.value = value;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < value.length; i++){
			s += value[i];
			if(!(i == value.length - 1)) 
				s += ",";
		}
		return s;
	}

	public String[] getValue() {
		return value;
	}
}
