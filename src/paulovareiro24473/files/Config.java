package paulovareiro24473.files;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Config {

	protected String name;
	protected File file;
	protected File directory;
	
	protected ArrayList<Key> keys = new ArrayList<Key>();

	public Config(String dir,String name){
		this.name = name; 
		try{
			directory = new File(dir);
			file = new File(directory,name);
			if(!directory.exists()){
				directory.mkdir();
			}
			if(!file.exists()){
				file.createNewFile();
			}else{
				refreshKeys();
			}
		}catch(IOException e){
			
		}
	}

	public boolean isEmpty(){
		if(keys.size() == 0)
			return true;
		return false;
	}
	
	public Config(File file){
		this.name = file.getName();
		this.file = file;
		this.directory = new File(file.getPath());
	}
	
	
	private void refreshKeys(){ //usado para ler do ficheiro as keys
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			while(line != null){
				if(line.length() == 0){
					line=br.readLine();
					continue;
				}
				String[] sections = line.split(";"); //separa por ; e guarda no array
				
				Key key = new Key(sections[0]); //cria a key com  o respetivo nome da key
				for(int i = 1; i < sections.length; i++){
					Value v = new Value(sections[i].split(","));
					key.addValue(v);
				}
				addKey(key); //adiciona aquela key
				line = br.readLine();
			}
			
			br.close();
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public Key getKeyByName(String keyName){
		for(Key key : keys){
			if(key.getName().equals(keyName)){
				return key;
			}
		}
		
		return null;
	}
	
	public void refresh(){ //usado para escrever para o ficheiro as keys
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(Key key : keys){
				bw.write(key.getName() + ";");

				for(Value value : key.getValues()){

					bw.write(value.toString() + ";");
				}
				bw.write("\n");
			}
			
			bw.flush();
			bw.close();
			
		}catch(IOException e){}
	}
	
	public boolean addKey(Key key){
		for(int i = 0; i < keys.size(); i++){
			if(keys.get(i).getName().equals(key.getName())){
				keys.set(i, key);	
				refresh();			
				return true;
				
			}
		}
		keys.add(key);
		refresh();
		return true;
	}
	
	public boolean addKey(String key,Value[] values){
		Key k = new Key(key);
		k.setValues(values);
		return addKey(k);
	}
	
	public boolean removeKey(String key){
		Key toRemove = null;
		for(Key k : keys){
			if(k.getName().equals(key)){
				toRemove = k;
			}
		}
		if(toRemove == null)
			return false;
		
		keys.remove(toRemove);
		refresh();
		return true;
	}
	
	public ArrayList<Key> getKeys(){
		return keys;
	}
	
	public String getFileName(){
		return file.getName();
	}
	
}
