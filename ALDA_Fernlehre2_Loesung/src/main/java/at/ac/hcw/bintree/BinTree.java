package at.ac.hcw.bintree;

/**
 * Starter implementation of a binary search tree.
 *
 * @param <T> the type of data stored in the tree
 */
public class BinTree<T> {
    private Node<T> root;
    private int nodeCount;

    /**
     * Inserts a new key-value pair into the tree.
     * If the key already exists, the associated data should be updated.
     *
     * @param key the key that defines the node position
     * @param data the payload that should be stored
     */
    public void insert(int key, T data) {
        root = insertNode(root, key, data);
    }

    /**
     * Recursively inserts a key-value pair into the subtree rooted at {@code node}.
     *
     * @param node the current subtree root
     * @param key the key to insert
     * @param data the payload to insert
     * @return the updated subtree root
     */
    private Node<T> insertNode(Node<T> node, int key, T data) {
        if (node == null) {
            nodeCount++;
            return new Node<>(key, data);
        }

        if (key < node.getKey()) {
            node.setLeft(insertNode(node.getLeft(), key, data));
        } else if (key > node.getKey()) {
            node.setRight(insertNode(node.getRight(), key, data));
        } else {
            // Derselbe Schluessel bekommt neue Daten, aber keinen neuen Knoten.
            node.setData(data);
        }

        return node;
    }

    /**
     * Returns the current root node of the tree.
     *
     * @return the root node, or {@code null} if the tree is empty
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Returns the number of distinct nodes currently stored in the tree.
     *
     * @return the node count
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Looks up the payload for a given key.
     *
     * @param key the key to search for
     * @return the stored payload, or {@code null} if the key does not exist
     */
    public T lookup(int key) {
        Node<T> node = lookupNode(root, key);
        return node == null ? null : node.getData();
    }

    /**
     * Recursively searches for the node that matches a given key.
     *
     * @param node the current subtree root
     * @param key the key to search for
     * @return the matching node, or {@code null} if it does not exist
     */
    private Node<T> lookupNode(Node<T> node, int key) {
        if (node == null || key == node.getKey()) {
            return node;
        }

        if (key < node.getKey()) {
            return lookupNode(node.getLeft(), key);
        }
        return lookupNode(node.getRight(), key);
    }

    /**
     * Prints the tree level by level to the console.
     */
    public void printLevelOrder() {
        int depth = treeDepth(root);
        for (int level = 1; level <= depth; level++) {
            printLevel(root, level);
            System.out.println();
        }
    }

    /**
     * Computes the maximum depth of the subtree rooted at {@code node}.
     *
     * @param node the current subtree root
     * @return the depth of the subtree
     */
    private int treeDepth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(node.getLeft()), treeDepth(node.getRight()));
    }

    /**
     * Prints all nodes that belong to the requested level.
     *
     * @param node the current subtree root
     * @param targetLevel the level that should be printed, starting at 1
     */
    private void printLevel(Node<T> node, int targetLevel) {
        if (node == null) {
            return;
        }
        if (targetLevel == 1) {
            System.out.print(node.getKey() + " ");
            return;
        }
        printLevel(node.getLeft(), targetLevel - 1);
        printLevel(node.getRight(), targetLevel - 1);
    }
}

