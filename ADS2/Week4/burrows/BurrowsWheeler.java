import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
   private static final int CHAR_BITS = 8;
   public static void transform() {
       String input = BinaryStdIn.readString();
        CircularSuffixArray cSA = new CircularSuffixArray(input);
        for (int i = 0; i < cSA.length(); i++) {
            if (cSA.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < cSA.length(); i++) {
            int index = cSA.index(i);
            if (index == 0) {
                BinaryStdOut.write(input.charAt(input.length() - 1), CHAR_BITS);
                continue;
            }
            BinaryStdOut.write(input.charAt(index - 1), CHAR_BITS);
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String ch = BinaryStdIn.readString();
        char[] chArray = ch.toCharArray();
        int[] next = new int[chArray.length];
        Map<Character, Queue<Integer>> positions = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < chArray.length; i++) {
            if (!positions.containsKey(chArray[i])) {
                positions.put(chArray[i], new Queue<Integer>());
            }
            positions.get(chArray[i]).enqueue(i);
        }
        Arrays.sort(chArray);
        for (int i = 0; i < chArray.length; i++) {
            next[i] = positions.get(chArray[i]).dequeue();
        }
        int curRow = first;
        for (int i = 0; i < chArray.length; i++) {
            BinaryStdOut.write(chArray[curRow]);
            curRow = next[curRow];
            BinaryStdOut.close();
        }
    }
    public static void main(String[] args) {
         /**
         * This is main method.
         */
   }
}