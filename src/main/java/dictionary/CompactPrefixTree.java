package dictionary;

import java.util.Locale;

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
     * contains nodes listed using pre-order traversal and uses indentations to show the level of the node.
     * An asterisk after the node means the node's boolean flag is set to true.
     * The root is at the current indentation level (followed by * if the node's valid bit is set to true),
     * then there are children of the node at a higher indentation level.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // FILL IN CODE

        return sb.toString();
    }

    /**
     * Print out the nodes of the tree to a file, using indentations to specify the level
     * of the node.
     * @param filename the name of the file where to output the tree
     */
    public void printTree(String filename) {
        // FILL IN CODE
        // Uses toString() method; outputs info to a file


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


        return null; // don't forget to change it
    }

    // ---------- Private helper methods ---------------

    /**
     * A private add method that adds a given string to the tree
     * @param s the string to add
     * @param node the root of a tree where we want to add a new string

     * @return a reference to the root of the tree that contains s
     */
    private Node add(String s, Node node) {
        // TODO: do insert first
        // Base case 1: empty tree
        // create a new node whose prefix is the word s and isWord true, return this node
        if (node == null) {
            Node root = new Node(true, s);
            return root;
        }

        // A node whose prefix is the same as the word you are looking for,
        // with the valid bit set to false. Set this bit to true, and return the tree.
        if (node.prefix.equals(s)) {
            if (!node.isWord) {
                node.isWord = true;
                return node;
            }
            //The word is already in the tree! Return the tree unchanged.
            if (node.isWord) {
                return node;
            }

            //A node whose prefix is not the prefix of the word you are looking for.
            // This is the hard case. Example: if you were inserting "hamster"
            // into a node whose prefix was "hamburger". You need to:

            // Create a new node.
            // The prefix stored in this node is the longest common prefix of the word you are inserting
            // and the prefix stored at the original root.
            //
            // Thus, if you were inserting "hamster" into a node whose prefix was "hamburger",
            // then the prefix of this new node would be "ham"
        }


        return null; // don't forget to change it
    }

    public String longestCommonPrefix (String s, Node n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == n.prefix.charAt(i)) {
                sb.append(s.charAt(i));
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
        // FILL IN CODE
        // Base Cases: If the tree is empty, return false
        if (node == null) {
            return false;
        }

        // If word does not start with the prefix stored at the root, return false
        if (!s.toLowerCase().startsWith(node.prefix)) {
            return false;
        } else if (s.toLowerCase().startsWith(node.prefix)){ // word = prefix stored at the root
            if (!node.isWord) {
                return false;
            } else {
                return true; // the word is in the tree
            }
        }
        //Recursive Case:
        //the prefix stored at the root of the tree is a proper prefix of the word we are looking for
        String subS = getSuffix(s.toLowerCase(), node);
//        char first = subS.charAt(0);
//        int index = first - 'a';
        return checkPrefix(subS, node.children[getIndex(subS)]);
    }

    /**
     * Get suffix, the portion of the word that is not part of the prefix stored at the root.
     * So, if the word we are looking for is "green",
     * and the prefix stored at the root of the tree is "gre", then suffix would be "en"
     * @param pref String to be searched and suffixed
     * @param node node of the tree
     * @return suffix of the input string
     */
    private String getSuffix (String pref, Node node) {
        return pref.substring(node.prefix.length());
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
        // BASE CASE
        // If tree is null, return false
        if (node == null) {
            return false;
        }
        // If prefix of the root starts with P, return true
        if (node.prefix.startsWith(prefix.toLowerCase())) {
            return true;
        }
        // RECURSIVE CASE
        // if prefix starts with the prefix stored at the root,
        // remove prefix from P
        // recursively check the subtree

        // String prefix: cart
        // Node ca
        // if prefix of the root starts with p, return true
        // Make sure you DO NOT iterate over the children but to search in only one child subtree
        if (prefix.toLowerCase().startsWith(node.prefix)) {
            return checkPrefix(getSuffix(prefix, node), node);
        }
        return false;
    }



    // You might want to create a private recursive helper method for toString
    // that takes the node and the number of indentations, and returns the tree  (printed with indentations) in a string.
    // private String toString(Node node, int numIndentations)


    // Add a private suggest method. Decide which parameters it should have

    // --------- Private class Node ------------
    // Represents a node in a compact prefix tree
    class Node {
        String prefix; // prefix stored in the node
        Node children[]; // array of children (26 children)
        boolean isWord; // true if by concatenating all prefixes on the path from the root to this node, we get a valid word

        Node() {
            isWord = false;
            prefix = "";
            children = new Node[26]; // initialize the array of children
        }

        /**
         * A helper constructor to create a new node with boolean and string value
         * @param valid boolean isWord valid
         * @param pref String prefix
         */
        Node(boolean valid, String pref) {
            isWord = valid;
            prefix = pref;
        }

        // FILL IN CODE: Add other methods to class Node as needed
    }

}
