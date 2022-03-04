package paulovareiro24473.options.menuOptions;

import java.io.File;

import paulovareiro24473.Logic;
import paulovareiro24473.files.Config;
import paulovareiro24473.files.Key;
import paulovareiro24473.files.Value;
import paulovareiro24473.options.Option;

public class RankingOption extends Option{

	public RankingOption() {
		super("Level Ranking", "top10","ranking");
	}

	@Override
	public void execute() {
		File folder = new File(Logic.savesDir);
		Key[] array = new Key[folder.listFiles().length]; //aproveitar o "key" das configuraçoes, para guardar o nome e nivel no ranking. 
		
		if(folder.listFiles().length == 0){
			Logic.printHeading("Ranking is empty");
			Logic.getch();
			Logic.cls();
			return;
		}
		
		for(int i = 0; i < folder.listFiles().length; i++){
			if((new File(folder.listFiles()[i].getPath(),"player.save").exists())){ // se o ficheiro player.save exists vai dar load temporariamente para ir buscar o nivel
				Config pConfig = new Config(folder.listFiles()[i].getPath(),"player.save");
				Key playerinfo = pConfig.getKeys().get(0);
				
				Key temp = new Key(playerinfo.getName());
				temp.setValues(new Value[]{new Value("" + Integer.valueOf(playerinfo.getValues()[0].getValue()[0]))});
				array[i] = temp;
			}
		}
		
		array = sort(array);
		Logic.printHeading("Ranking top 10");
		
		if(array.length < 10){
			for(int i = 0; i < array.length; i++){
				Logic.println("   [" + (i+1) + "]\t" + array[i].getName() + " : " + array[i].getValues()[0]);
			}
		}else{
			for(int i = 0; i < 10; i++){
				Logic.println("   [" + (i+1) + "]\t" + array[i].getName() + " : " + array[i].getValues()[0]);
			}
		}
		Logic.separatorln(Logic.lastSeperatorSize);
		
		Logic.getch();
		Logic.cls();
	}

	public Key[] sort(Key[] array){
		Key temp;
		for(int i = 0; i < array.length; i++){ //percorre o array todo
			for(int j = 1; j < array.length - i; j++){ //em cada iteraçao , percorre outra vez o array todo 
				if(Integer.valueOf(array[j - 1].getValues()[0].getValue()[0]) < Integer.valueOf(array[j].getValues()[0].getValue()[0])){ 
					//se o numero atual for maior que o numero anterior vai fazer uma troca de esses valores. Ao fim de toda a iteraçao, o array vai estar todo ordenado
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
}
