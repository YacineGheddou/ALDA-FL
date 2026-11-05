package at.ac.hcw.bintree;

/**
 * Starter implementation of an AVL tree.
 *
 * @param <T> the type of data stored in the tree
 */
public class AvlTree<T> {
    private Node<T> root;
    private int nodeCount;

    /**
     * Inserts a new key-value pair into the AVL tree.
     * If the key already exists, the associated data should be updated.
     *
     * @param key the key that defines the node position
     * @param data the payload that should be stored
     */
    public void insert(int key, T data) {
        root = insertNode(root, key, data);
    }

    /**
     * Recursively inserts a key-value pair into the subtree rooted at {@code node}
     * and restores AVL balance afterwards.
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
            node.setData(data);
            return node;
        }

        updateHeight(node);
        return rebalance(node);
    }

    /**
     * Restores AVL balance for the subtree rooted at {@code node}.
     *
     * @param node the subtree root that may be unbalanced
     * @return the balanced subtree root
     */
    private Node<T> rebalance(Node<T> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            return getBalance(node.getLeft()) >= 0
                    ? rotateRight(node)
                    : rotateLeftRight(node);
        }

        if (balance < -1) {
            return getBalance(node.getRight()) <= 0
                    ? rotateLeft(node)
                    : rotateRightLeft(node);
        }

        return node;
    }

    /**
     * Performs a left rotation around the given node.
     *
     * @param node the subtree root before rotation
     * @return the new subtree root after rotation
     */
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newRoot = node.getRight();
        Node<T> movedSubtree = newRoot.getLeft();

        newRoot.setLeft(node);
        node.setRight(movedSubtree);

        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Performs a right rotation around the given node.
     *
     * @param node the subtree root before rotation
     * @return the new subtree root after rotation
     */
    private Node<T> rotateRight(Node<T> node) {
        Node<T> newRoot = node.getLeft();
        Node<T> movedSubtree = newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(movedSubtree);

        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * Performs a left-right double rotation.
     *
     * @param node the subtree root before rotation
     * @return the new subtree root after rotation
     */
    private Node<T> rotateLeftRight(Node<T> node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }

    /**
     * Performs a right-left double rotation.
     *
     * @param node the subtree root before rotation
     * @return the new subtree root after rotation
     */
    private Node<T> rotateRightLeft(Node<T> node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }

    /**
     * Recomputes and stores the height of the given node.
     *
     * @param node the node whose height should be updated
     */
    private void updateHeight(Node<T> node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    /**
     * Returns the height of a node and treats {@code null} as height 0.
     *
     * @param node the node whose height should be read
     * @return the stored height, or 0 for {@code null}
     */
    private int height(Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    /**
     * Computes the AVL balance factor of a node.
     *
     * @param node the node whose balance factor should be computed
     * @return left subtree height minus right subtree height
     */
    private int getBalance(Node<T> node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    /**
     * Returns the current root node of the AVL tree.
     *
     * @return the root node, or {@code null} if the tree is empty
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Returns the number of distinct nodes currently stored in the AVL tree.
     *
     * @return the node count
     */
    public int getNodeCount() {
        return nodeCount;
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

