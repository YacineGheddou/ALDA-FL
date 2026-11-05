package at.ac.hcw.bintree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BinTreeTest {

    @Test
    void insertCreatesRootNode() {
        BinTree<String> tree = new BinTree<>();

        tree.insert(50, "Luke Skywalker");

        assertEquals(50, tree.getRoot().getKey());
        assertEquals("Luke Skywalker", tree.getRoot().getData());
        assertEquals(1, tree.getNodeCount());
    }

    @Test
    void insertPlacesSmallerKeysLeftAndGreaterKeysRight() {
        BinTree<String> tree = new BinTree<>();

        tree.insert(50, "Luke Skywalker");
        tree.insert(30, "Leia Organa");
        tree.insert(70, "Darth Vader");

        assertEquals(30, tree.getRoot().getLeft().getKey());
        assertEquals(70, tree.getRoot().getRight().getKey());
    }

    @Test
    void duplicateKeyUpdatesDataWithoutIncreasingNodeCount() {
        BinTree<String> tree = new BinTree<>();

        tree.insert(50, "Luke Skywalker");
        tree.insert(50, "Master Luke");

        assertEquals(1, tree.getNodeCount());
        assertEquals("Master Luke", tree.lookup(50));
    }

    @Test
    void lookupReturnsStoredDataForExistingKey() {
        BinTree<String> tree = new BinTree<>();

        tree.insert(50, "Luke Skywalker");
        tree.insert(30, "Leia Organa");
        tree.insert(70, "Darth Vader");
        tree.insert(60, "Obi-Wan Kenobi");

        assertEquals("Obi-Wan Kenobi", tree.lookup(60));
    }

    @Test
    void lookupReturnsNullForMissingKey() {
        BinTree<String> tree = new BinTree<>();

        tree.insert(50, "Luke Skywalker");
        tree.insert(30, "Leia Organa");

        assertNull(tree.lookup(99));
    }
}

