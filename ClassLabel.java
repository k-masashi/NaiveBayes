package tutorial.bayes;
import java.util.ArrayList;

public class ClassLabel {
	private ArrayList<String[]> documents;
	private Vocabulary voc;
	private int[] vocCount;
	private Double[] vocP;
	private Double p_class;
	private String labelName;
	
	public ClassLabel(Vocabulary v, String label){
		documents = new ArrayList<String[]>();
		labelName = label;
		voc = v;
		vocCount = new int[voc.getVoc().size()];
	}
	
	public void makeClassfier(){
		int vocN = 0;
		for(int k=0; k < vocCount.length; k++){
			vocN += vocCount[k];
		}
		
		vocP = new Double[vocCount.length];
		for(int i=0; i < vocCount.length; i++){
			vocP[i] = (double)(vocCount[i]+1) /  (double)(vocN + vocCount.length);  
		}
	}
	
	public Double classify(String[] d){
		Double result = 1.0;
		result *= getP_class();
		for(int i=0; i < d.length; i++){
			if(voc.getVoc().contains(d[i])){
				result *=  vocP[voc.getCode(d[i])];
			}
		}
		return result;
	}
	
	public String getLabel(){
		return labelName;
	}
	
	public void addDocument(String[] array){
		documents.add(array);
		for(int i=0; i < array.length; i++){
			vocCount[voc.getCode(array[i])] += 1;
		}
	}
		
	public ArrayList<String[]> getDocuments(){
		return documents;
	}
	
	public Double[] getP(){
		return vocP;
	}
	
	public void setP_class(Double p){
		p_class = p;
	}
	
	public Double getP_class(){
		return p_class;
	}
	
}