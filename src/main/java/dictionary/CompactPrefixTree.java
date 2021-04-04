package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/** CompactPrefixTree class, implements Dictionary ADT and
 *  several additional methods. Can be used as a spell checker.
 *  Fill in code and feel free to add additional methods as needed.
 *  S21 */
public class CompactPrefixTree implements Dictionary {

    private Node root; // the root of the tree

    /** Default constructor  */
    public CompactPrefixTree() { }

    /**
     * Creates a dictionary ("compact prefix tree")
     * using words from the given file.
     * @param filename the name of the file with words
     */
    public CompactPrefixTree(String filename) {
        // FILL IN CODE:
        // Read each word from the file, add it to the tree
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String s;
            while ((s = br.readLine()) != null) {
                add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Adds a given word to the dictionary.
     * @param word the word to add to the dictionary
     */
    public void add(String word) {
        root = add(word.toLowerCase(), root); // Calling private add method
    }

    /**
     * Checks if a given word is in the dictionary
     * @param word the word to check
     * @return true if the word is in the dictionary, false otherwise
     */
    public boolean check(String word) {
        return check(word.toLowerCase(), root); // Calling private check method
    }

    /**
     * Checks if a given prefix is stored in the dictionary
     * @param prefix The prefix of a word
     * @return true if this prefix is a prefix of any word in the dictionary,
     * and false otherwise
     */
    public boolean checkPrefix(String prefix) {
        return checkPrefix(prefix.toLowerCase(), root); // Calling private checkPrefix method
    }

    /**
     * Returns a human-readable string representation of the compact prefix tree;
     * contains nodes listed using pre-order traversal (root - left - right) and uses indentations to show the level of the node.
     * An asterisk after the node means the node's boolean flag is set to true.
     * The root is at the current indentation level (followed by * if the node's valid bit is set to true),
     * then there are children of the node at a higher indentation level.
     */
    public String toString() {
        return toString(this.root, 0);
    }

    /**
     * a private recursive helper method for toString
     * that takes the node and the number of indentations, and returns the tree
     * @param node Tree Node
     * @param numIndentations the number of indentations
     * @return String
     */
    private String toString(Node node, int numIndentations) {
        StringBuilder sb = new StringBuilder();
        buildString(node, numIndentations, sb);
        for (Node n: node.children) {
            if (n != null) {
                numIndentations++;
                buildString(n, numIndentations, sb);
                for (int i = 0; i < n.children.length; i++) {
                    if (n.children[i] != null) {
                        numIndentations++;
                        sb.append(toString(n.children[i], numIndentations));
                        numIndentations--;
                    }
                }
                numIndentations--;
            }
        }
        return sb.toString();
    }

    /**
     * a helper method to insert indentation and asterisk
     * @param node current node
     * @param numIndentations number of indentation
     * @param sb string builder
     */
    private void buildString(Node node, int numIndentations, StringBuilder sb) {
        sb.append(" ".repeat(Math.max(0, numIndentations)));
        sb.append(node.prefix);
        if (node.isWord) {
            sb.append("*");
        }
        sb.append("\n");
    }

    /**
     * Print out the nodes of the tree to a file, using indentations to specify the level
     * of the node.
     * @param filename the name of the file where to output the tree
     */
    public void printTree(String filename) {
        String s = toString();
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Return an array of the entries in the dictionary that are as close as possible to
     * the parameter word.  If the word passed in is in the dictionary, then
     * return an array of length 1 that contains only that word.  If the word is
     * not in the dictionary, then return an array of numSuggestions different words
     * that are in the dictionary, that are as close as possible to the target word.
     * Implementation details are up to you, but you are required to make it efficient
     * and make good use ot the compact prefix tree.
     *
     * @param word The word to check
     * @param numSuggestions The length of the array to return.  Note that if the word is
     * in the dictionary, this parameter will be ignored, and the array will contain a
     * single world.
     * @return An array of the closest entries in the dictionary to the target word
     */

    public String[] suggest(String word, int numSuggestions) {
        // FILL IN CODE
        // Note: you need to create a private suggest method in this class
        // (like we did for methods add, check, checkPrefix)

        // travel to the right node
        // then helper method get the children
        // find closest
        // return suggest(this.root);

        if (check(word)) {
            return new String[] { word };
        } else {
            // find lcp, the lcp doesn't have to be in a single node e.g word is car, dictionary has cart
            // go to the child that starts with the word e.g. go to child c
            // get the suffix e.g. remove ca from car, we get r
            // go down until we find the word that doesn't match e.g. go to the r child, here we have rt, lcp is r, but rt is longer so we stop

            // send task to helper, get suggestions list
            // check length of the list, if it's not enough, add my own suggestions
            // keep track the number of suggestions, the moment you have enough, you can return
            Node childRoot = findRoot(word, this.root);
            String lcp = longestCommonPrefix(word, childRoot.prefix);
            return new String[] { lcp };
        }
        //return null;
    }


    private Node findRoot (String word, Node curNode) {
        if (curNode == null) {
            return null;
        }
        Node childRoot = curNode.children[getIndex(String.valueOf(word.charAt(0)))];
        if (childRoot != null) {
            String lcp = longestCommonPrefix(word, childRoot.prefix);
            if (checkPrefix(lcp)){
                String suffixWord = getSuffix(word, lcp.length());
                int index = getIndex(String.valueOf(suffixWord.charAt(0)));
                childRoot = childRoot.children[index];
                return findRoot(suffixWord, childRoot);
            }
        }
        return curNode;
    }
    // ---------- Private helper methods ---------------
    // Add a private suggest method. Decide which parameters it should have
    // A helper function, given the node, get all the valid word in the children --> you can use the helper function
    private ArrayList<String> suggest(Node root, int numSuggestions) {
        ArrayList<String> suggestions = new ArrayList<>();
        // find the parent
        // create a helper method that get all the children
        // loop through each children and get the lcp
        // return word with the longest lcp
        // Bryan: build suggestions otw back up
        // it also depends on the number of suggestions
        // if the numSuggestions > numChildren, go to the root after and get a word, we go left, right, underneath

        return suggestions;
    }
    /**
     * A private add method that adds a given string to the tree
     * @param s the string to add
     * @param node the root of a tree where we want to add a new string

     * @return a reference to the root of the tree that contains s
     */
    private Node add(String s, Node node) {
        if (node == null) {
            Node root = new Node(true, s);
            return root;
        }

        if (node.prefix.equals(s)) {
            if (!node.isWord) {
                node.isWord = true;
            }
            return node;
        }

        String lcp = longestCommonPrefix(s, node.prefix);
        if (lcp.equals(node.prefix)) {
            String suffixWord = getSuffix(s, node.prefix.length());
            int indexSuffixWord = getIndex(String.valueOf(suffixWord.charAt(0)));
            node.children[indexSuffixWord] = add(suffixWord, node.children[indexSuffixWord]);
            return node;
        }

        Node newNode = new Node();
        newNode.prefix = lcp;
        String suffix = getSuffix(node.prefix, newNode.prefix.length());
        node.prefix = suffix;
        newNode.children[getIndex(String.valueOf(suffix.charAt(0)))] = node;

        String suffixWord = getSuffix(s, newNode.prefix.length());
        int indexSuffixWord = getIndex(String.valueOf(suffixWord.charAt(0)));
        newNode.children[indexSuffixWord] = add(suffixWord, newNode.children[indexSuffixWord]);
        return newNode;
    }

    /**
     * a helper method to find the longest common prefix
     * @param s word
     * @param n node.prefix
     * @return string longest common prefix between the two
     */
    private String longestCommonPrefix (String s, String n) {
        if (n.equals("")) {
            return "";
        }
        int length = (Math.min(s.length(), n.length()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == n.charAt(i)) {
                sb.append(s.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    /** A private method to check whether a given string is stored in the tree.
     *
     * @param s the string to check
     * @param node the root of a tree
     * @return true if the prefix is in the dictionary, false otherwise
     */
    private boolean check(String s, Node node) {
        if (node == null) return false;
        if (!s.startsWith(node.prefix)) return false;
        if (s.equals(node.prefix)) {
            return node.isWord;
        }
        String subS = getSuffix(s, node.prefix.length());
        return check(subS, node.children[getIndex(subS)]);
    }

    /**
     * Get suffix, the portion of the word that is not part of the prefix stored at the root.
     * So, if the word we are looking for is "green",
     * and the prefix stored at the root of the tree is "gre", then suffix would be "en"
     * @param pref String to be searched and suffixed
     * @param idx int beginning index of the substring
     * @return suffix of the input string
     */
    private String getSuffix (String pref, int idx) {
        return pref.substring(idx);
    }

    /**
     * A helper method to get the index from substring
     * @param subS sub String
     * @return integer index
     */
    private int getIndex (String subS) {
        return subS.charAt(0) - 'a';
    }

    /**
     * A private recursive method to check whether a given prefix is in the tree
     *
     * @param prefix the prefix
     * @param node the root of the tree
     * @return true if the prefix is in the dictionary, false otherwise
     */
    private boolean checkPrefix(String prefix, Node node) {
        if (node == null) {
            return false;
        }
        if (node.prefix.startsWith(prefix)) {
            return true;
        }
        if (prefix.startsWith(node.prefix)) {
            String subS = getSuffix(prefix, node.prefix.length());
            return checkPrefix(subS, node.children[getIndex(subS)]);
        }
        return false;
    }

    // --------- Private class Node ------------
    // Represents a node in a compact prefix tree
    class Node {
        String prefix; // prefix stored in the node
        Node[] children; // array of children (26 children)
        boolean isWord; // true if by concatenating all prefixes on the path from the root to this node, we get a valid word

        Node() {
            isWord = false;
            prefix = "";
            children = new Node[26];
        }

        /**
         * A helper constructor to create a new node with boolean and string value
         * @param valid boolean isWord valid
         * @param pref String prefix
         */
        Node(boolean valid, String pref) {
            isWord = valid;
            prefix = pref;
            children = new Node[26];
        }

        public String getPrefix() {
            return prefix;
        }
    }

}
