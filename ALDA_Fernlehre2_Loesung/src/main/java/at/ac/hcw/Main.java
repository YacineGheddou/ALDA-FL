package at.ac.hcw;

import at.ac.hcw.bintree.AvlTree;
import at.ac.hcw.bintree.BinTree;

/**
 * Entry point for trying out the implementation manually.
 */
public class Main {
    /**
     * Creates example tree instances that you can use for experiments.
     *
     * @param args command-line arguments, not used in this project
     */
    public static void main(String[] args) {
        BinTree<String> binTree = new BinTree<>();
        AvlTree<String> avlTree = new AvlTree<>();

        int[] keys = {10, 20, 30, 40, 50};
        for (int key : keys) {
            binTree.insert(key, "Wert " + key);
            avlTree.insert(key, "Wert " + key);
        }

        System.out.println("Normaler binaerer Suchbaum:");
        binTree.printLevelOrder();

        System.out.println("\nAusgeglichener AVL-Baum:");
        avlTree.printLevelOrder();
    }
}

