package tutorial.bayes;

import java.util.ArrayList;

public class Vocabulary {
	private ArrayList<String> words;
	Vocabulary(){
		words = new ArrayList<String>();
	}
	
	public void addVoc(String[] d){
		for(int i=0; i < d.length; i++){
			if(!words.contains(d[i])){
				words.add(d[i]);
			}
		}
	}
	
	public int getCode(String str){
		int code = 0;
		for(int i=0; i < words.size(); i++){
			if(str.equals(words.get(i))){
				code = i;
			}
		}
		return code;
	}
	
	public ArrayList<String> getVoc(){
		return words;
	}
}
