package at.ac.hcw.bintree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvlTreeTest {

    @Test
    void insertCountsNodesInAvlTree() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(30, "Anakin Skywalker");
        tree.insert(20, "Ahsoka Tano");
        tree.insert(40, "Padme Amidala");

        assertEquals(3, tree.getNodeCount());
    }

    @Test
    void rightRotationBalancesLeftLeftCase() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(30, "Anakin Skywalker");
        tree.insert(20, "Ahsoka Tano");
        tree.insert(10, "Rex");

        assertEquals(20, tree.getRoot().getKey());
        assertEquals(10, tree.getRoot().getLeft().getKey());
        assertEquals(30, tree.getRoot().getRight().getKey());
    }

    @Test
    void leftRotationBalancesRightRightCase() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(10, "Rex");
        tree.insert(20, "Ahsoka Tano");
        tree.insert(30, "Anakin Skywalker");

        assertEquals(20, tree.getRoot().getKey());
        assertEquals(10, tree.getRoot().getLeft().getKey());
        assertEquals(30, tree.getRoot().getRight().getKey());
    }

    @Test
    void leftRightRotationBalancesLeftRightCase() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(30, "Anakin Skywalker");
        tree.insert(10, "Rex");
        tree.insert(20, "Ahsoka Tano");

        assertEquals(20, tree.getRoot().getKey());
        assertEquals(10, tree.getRoot().getLeft().getKey());
        assertEquals(30, tree.getRoot().getRight().getKey());
    }

    @Test
    void rightLeftRotationBalancesRightLeftCase() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(10, "Rex");
        tree.insert(30, "Anakin Skywalker");
        tree.insert(20, "Ahsoka Tano");

        assertEquals(20, tree.getRoot().getKey());
        assertEquals(10, tree.getRoot().getLeft().getKey());
        assertEquals(30, tree.getRoot().getRight().getKey());
    }

    @Test
    void leftRightRotationPreservesGrandchildrenSubtrees() {
        AvlTree<String> tree = new AvlTree<>();

        tree.insert(50, "Yoda");
        tree.insert(20, "Leia Organa");
        tree.insert(70, "Darth Vader");
        tree.insert(10, "R2-D2");
        tree.insert(30, "Luke Skywalker");
        tree.insert(25, "Han Solo");

        assertEquals(30, tree.getRoot().getKey());
        assertEquals(20, tree.getRoot().getLeft().getKey());
        assertEquals(50, tree.getRoot().getRight().getKey());
        assertEquals(10, tree.getRoot().getLeft().getLeft().getKey());
        assertEquals(25, tree.getRoot().getLeft().getRight().getKey());
        assertEquals(70, tree.getRoot().getRight().getRight().getKey());
    }
}

