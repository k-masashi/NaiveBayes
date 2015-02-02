package tutorial.bayes;
import java.util.ArrayList;

public class Bayes_multinomial {
	private ArrayList<ClassLabel> classLabels;
	public Bayes_multinomial(){
		Vocabulary voc = new Vocabulary();
		String[] d1 = {"good","bad","good","good"};
		String[] d2 = {"exciting","exciting"};
		String[] d3 = {"good","good","exciting","boring"};
		String[] d4 = {"bad","boring","boring","boring"};
		String[] d5 = {"bad","good","bad"};
		String[] d6 = {"bad","bad","boring","exciting"};
		voc.addVoc(d1);
		voc.addVoc(d2);
		voc.addVoc(d3);
		voc.addVoc(d4);
		voc.addVoc(d5);
		voc.addVoc(d6);
		
		ClassLabel c_p = new ClassLabel(voc,"positive");
		ClassLabel c_n = new ClassLabel(voc,"negative");
		c_p.addDocument(d1);		
		c_p.addDocument(d2);
		c_p.addDocument(d3);
		c_n.addDocument(d4);		
		c_n.addDocument(d5);
		c_n.addDocument(d6);		
		
		classLabels = new ArrayList<ClassLabel>();
		classLabels.add(c_p);
		classLabels.add(c_n);
		
		int N = 0;
		for(int k=0; k < classLabels.size(); k++){
			N += classLabels.get(k).getDocuments().size();
		}		
		int alpha = classLabels.size();
		N += alpha;
		
		for(int i=0; i < classLabels.size(); i++){
			classLabels.get(i).setP_class((double)((classLabels.get(i).getDocuments().size() + 1)) / (double)(N));
			classLabels.get(i).makeClassfier();
			System.out.println("P" + i + "=" + classLabels.get(i).getP_class());
			for(int l=0; l < classLabels.get(i).getP().length; l++){
				System.out.println("q_" + voc.getVoc().get(l) + "," + classLabels.get(i).getLabel() + " = " + classLabels.get(i).getP()[l]);
			}
		}

	}
	
	public void Classifier(String[] checkDocument){
		Double[] result = new Double[classLabels.size()];
		for(int i=0; i < classLabels.size(); i++){			
			result[i] = classLabels.get(i).classify(checkDocument); 
		}
		
		System.out.println("====result====");
		Double maxResult = 0.0;
		int max = 0;
		
		for(int k=0; k < result.length; k++){
			if(maxResult < result[k]){
				max = k;
				maxResult = result[k];
			}
			System.out.println(classLabels.get(k).getLabel()  + " = " + result[k]);
		}
		
		System.out.println("分類 : " + classLabels.get(max).getLabel());
	}
	
}
