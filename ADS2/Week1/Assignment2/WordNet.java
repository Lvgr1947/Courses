/**
 * @author Venu
 * 
 * WordNet class and integrate Digraph class for adding edges 
 * and display the count of Vertices and Edges
 */
import java.io.*;
import java.util.*;

public class WordNet {

    // ArrayList for keys_hid of String type 
    static ArrayList<String> key_hid = new ArrayList<String>();
    // ArrayList for values_hid of String array type
    static ArrayList<String[]> values_hid = new ArrayList<String[]>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        WordNet w = new WordNet(); // object for WordNet to call method ;parseHypernyms'
        String hypernyms_file = "hypernyms.txt";
        w.parseHypernyms(hypernyms_file); // method call 'parseHypernyms'

        // Creating object for Digraph with parameter 'number of vertices'
        Digraph digraph = new Digraph(key_hid.size());
        int e = 0;
        for (int i = 0; i < key_hid.size(); i++) {
            if (values_hid.get(i) != null) {
                for (int j = 0; j < values_hid.get(i).length; j++) {
                    int d1 = Integer.parseInt(key_hid.get(i)); // converting of String to Integer type
                    // converting each value in String array to Integer type
                    int d2 = Integer.parseInt(values_hid.get(i)[j]);
                    digraph.addEdge(d1, d2); // adding the edge of two vertices
                    e++;
                }
            }
        }
        System.out.println("Edges:" + e); // displaying the number of edges
        // System.out.println(digraph.toString());
    }

    private void parseHypernyms(String filename) throws FileNotFoundException, IOException {
        File  read_hypernyms = new File(filename); // importing the file "hypernyms.txt"
        // reading the file "hypernyms.txt" file
        BufferedReader br = new BufferedReader(new FileReader(read_hypernyms)); 
        String st;
        int v = 0;
        int d1, d2;
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",",2);  // splitting the each line into two with "," seperated      
            v++;

            if (data.length > 1){
                key_hid.add(data[0]);
                values_hid.add(data[1].split(","));
            }
            else {
                key_hid.add(data[0]);
                values_hid.add(null);
            }
        }
        System.out.println("Vertices:" + v); // displaying the number of vertices
    }
}
