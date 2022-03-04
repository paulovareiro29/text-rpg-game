package paulovareiro24473.options;


public abstract class Option {
	
	protected String name;
	protected String[] alias;
	
	public Option(String name,String... alias){
		this.name = name;
		this.alias = alias;
	}
	
	public abstract void execute();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAlias(String[] alias){
		this.alias = alias;
	}
	
	public boolean checkAlias(String a){
		for(String ali : alias){
			if(ali.equalsIgnoreCase(a)){
				return true;
			}
		}
		return false;
	}
	
}
