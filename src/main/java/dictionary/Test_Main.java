package dictionary;

public class Test_Main {

    public static void main(String[] args) {
        CompactPrefixTree tree = new CompactPrefixTree();
        tree.add("train");
        tree.add("training");
        tree.add("bart");
        tree.add("barometer");
        tree.add("bus");
        tree.add("bar");
        tree.add("buses");
        tree.add("business");
        tree.add("busy");
        tree.add("ball");

        System.out.println("Check 'train', expected true, result: " + tree.check("train"));
        System.out.println("Check 'cat', expected false, result: " + tree.check("cat"));
        System.out.println("Check 'busy', expected true, result: " + tree.check("busy"));
        System.out.println("Check 'training', expected true, result: " + tree.check("training"));
        System.out.println("Check 'cow', expected false, result: " + tree.check("cow"));
        System.out.println("Check 'bart', expected true, result: " + tree.check("bart"));

        System.out.println("Check Prefix 'b', expected true, result: " + tree.checkPrefix("b"));
        System.out.println("Check Prefix 'bar', expected true, result: " + tree.checkPrefix("bar"));
        System.out.println("Check Prefix 'c', expected false, result: " + tree.checkPrefix("c"));
        System.out.println("Check Prefix 'ar', expected false, result: " + tree.checkPrefix("ar"));
        System.out.println(tree.toString());
    }
}
