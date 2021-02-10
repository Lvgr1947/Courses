import java.util.Arrays;

public class CircularSuffixArray {
    private final int length;
    private final int[] position;
    private final String s;
    private class  Node implements Comparable<Node> {
        private final int index;
        public Node(int index) {
            if (index < 0 || index >= length) {
                throw new IllegalArgumentException();
            }
            this.index = index;
        }
        public int getIndex() {
            return index;
        }
        public int compareTo(Node node) {
            int i = node.getIndex();
            for (int j = 0; j < length; j++) {
                int position1 = (j + index + length()) % length();
                int position2 = (j + i + length()) % length();
                if (s.charAt(position1) != s.charAt(position2)) {
                    return s.charAt(position1) - s.charAt(position2);
                }
            }
            return 0;
        }
    }

    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.position = new int[s.length()];
        this.length = s.length();
        this.s = s;
        Node[] suffix = new Node[s.length()];
        for (int i = 0; i < suffix.length; i++) {
            suffix[i] = new Node(i);
        }
        Arrays.sort(suffix);
        for (int i = 0; i < position.length; i++) {
            position[i] = suffix[i].getIndex();
        }
    }

    public int length() {
        return length;
    }

    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException();
        }
        return position[i];
    }

    public static void main(String[] args) {
        /**
         * This is main method.
         */
    }
}
