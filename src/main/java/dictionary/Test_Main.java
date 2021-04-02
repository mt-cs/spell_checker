package dictionary;

public class Test_Main {


    public static void main(String[] args) {
        CompactPrefixTree tree = new CompactPrefixTree();
        tree.add("train");
        tree.add("training");
        tree.add("bart");
        tree.add("barometer");
    }
}
