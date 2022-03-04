package paulovareiro24473.states;

import paulovareiro24473.STATE;
import paulovareiro24473.options.Option;

public abstract class State {

	protected String name;
	protected STATE state;
	
	protected Option[] options;
	
	public State(String name,STATE state){
		this.name = name;
		this.state = state;
	}
	
	public abstract void run();

	public String getName() {
		return name;
	}

	public STATE getState() {
		return state;
	}

	public Option[] getOptions(){
		return options;
	}
	
	public void addOption(Option opc) {
		Option[] opcs = new Option[options.length + 1 ];
		for(int i = 0; i < options.length-1; i++){
			opcs[i] = options[i];
		}
		opcs[options.length-1] = opc;
		opcs[opcs.length-1] = options[options.length-1];
		options = opcs;
	}
	
}
