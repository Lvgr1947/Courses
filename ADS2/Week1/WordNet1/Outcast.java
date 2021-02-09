/** 
 * @author L.venugopalrao
 *
 * This class Outcast used to find unrelated word in the
 * outcast*.txt files by calculating the maximum distances of those words 
 * in between them
*/

public class Outcast {

   private final WordNet wordnet;       // variable declaration for WordNet
   public Outcast(WordNet wordnet) {    // constructor takes a WordNet object
   		this.wordnet = wordnet;
   } 

   /** 
    * @param nouns input nouns of type String[] 
    * 
    * input an array of WordNet nouns, 
    * returns an outcast
   */      

   public String outcast(String[] nouns) {
   		int sample = 0;
   		String outcast = null;

   		for (String d : nouns) {
   			int distance = 0;
   			for (String h : nouns) {
   				int lengthDIstance = wordnet.distance(d, h);
   				distance += lengthDIstance;
   			}
   			if (distance > sample) {
   				sample = distance;
   				outcast = d;
   			}
   		}
   		return outcast;
   } 
}
