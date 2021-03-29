package dictionary;

public class Test_Main {
    private static class Node {
        String prefix; // prefix stored in the node
        CompactPrefixTree.Node children[]; // array of children (26 children)
        boolean isWord; // true if by concatenating all prefixes on the path
        // from the root to this node, we get a valid word

        Node() {
            isWord = false;
            prefix = "";
            children = new CompactPrefixTree.Node[26]; // initialize the array of children
        }
    }
    public static void main(String[] args) {
        Node root = new Node();
        root.prefix = "ca";
        root.isWord = false;
        String car = "car";
        System.out.println(car.toLowerCase().startsWith(root.prefix));
        System.out.println(car.substring(root.prefix.length()));
    }

}
