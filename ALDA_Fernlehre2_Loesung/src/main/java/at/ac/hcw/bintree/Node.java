package at.ac.hcw.bintree;

/**
 * Represents a single node inside a binary tree or AVL tree.
 *
 * @param <T> the type of payload stored in the node
 */
public class Node<T> {
    private final int key;
    private T data;
    private Node<T> left;
    private Node<T> right;
    private int height;

    /**
     * Creates a new node with the given key and payload.
     *
     * @param key the ordering key of the node
     * @param data the value stored in the node
     */
    public Node(int key, T data) {
        this.key = key;
        this.data = data;
        this.height = 1;
    }

    /**
     * Returns the key that is used to position this node in the tree.
     *
     * @return the key of this node
     */
    public int getKey() {
        return key;
    }

    /**
     * Returns the payload stored in this node.
     *
     * @return the stored payload
     */
    public T getData() {
        return data;
    }

    /**
     * Replaces the payload stored in this node.
     *
     * @param data the new payload
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the left child of this node.
     *
     * @return the left child, or {@code null} if none exists
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child reference of this node.
     *
     * @param left the node that should become the left child
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Returns the right child of this node.
     *
     * @return the right child, or {@code null} if none exists
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Sets the right child reference of this node.
     *
     * @param right the node that should become the right child
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * Returns the stored AVL height information of this node.
     *
     * @return the height value of this node
     */
    public int getHeight() {
        return height;
    }

    /**
     * Updates the stored AVL height information of this node.
     *
     * @param height the new height value
     */
    public void setHeight(int height) {
        this.height = height;
    }
}

