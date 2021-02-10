import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int r = 256;
    public static void encode() {
        char[] sequence = new char[r];
        for (int i = 0; i < r; i++) {
            sequence[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            for (int i = 0; i < sequence.length; i++) {
                if (sequence[i] == ch) {
                    break;
                }
                BinaryStdOut.write((char) i);
                for (int j = i; j > 0; j--) {
                    sequence[j] = sequence[j-1];
                }
                sequence[0] = ch;
            }
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    public static void decode() {
        char[] sequence = new char[r];
        for (int i = 0; i < r; i++) {
            sequence[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readChar();
            char ch = sequence[i];
            BinaryStdOut.write(ch);
            for (int j = i; j > 0; j--) {
                sequence[j] = sequence[j-1];
            }
            sequence[0] = ch;
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
         /**
         * This is main method.
         */
    }
}