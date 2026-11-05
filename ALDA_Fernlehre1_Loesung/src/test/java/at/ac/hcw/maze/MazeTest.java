package at.ac.hcw.maze;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    private static char[][] mazeOf(String... rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = rows[i].toCharArray();
        }
        return grid;
    }

    private static char[][] getInternalMaze(Maze maze) {
        try {
            Field field = Maze.class.getDeclaredField("maze");
            field.setAccessible(true);
            return (char[][]) field.get(maze);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new AssertionError("Unable to access maze field", e);
        }
    }

    private static boolean containsChar(char[][] grid, char ch) {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == ch) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    void testSimplePathRight() {
        char[][] grid = mazeOf(
                "#####",
                "#ST #",
                "#####"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 1);

        assertEquals(2, result);
        char[][] after = getInternalMaze(maze);
        assertEquals('.', after[1][1]);
        assertEquals('T', after[1][2]);
    }

    @Test
    void testStartOnTarget() {
        char[][] grid = mazeOf(
                "#####",
                "# T #",
                "#####"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 2);

        assertEquals(1, result);
        char[][] after = getInternalMaze(maze);
        assertEquals('T', after[1][2]);
    }

    @Test
    void testNoPathReturnsZeroAndMarksDeadEnd() {
        char[][] grid = mazeOf(
                "#####",
                "#S###",
                "###T#",
                "#####"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 1);

        assertEquals(0, result);
        char[][] after = getInternalMaze(maze);
        assertEquals('x', after[1][1]);
    }

    @Test
    void testUniquePathSimpleMaze() {
        char[][] grid = mazeOf(
                "#####",
                "# T #",
                "#   #",
                "##S##",
                "#####"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(3, 2);

        assertEquals(3, result);
        char[][] after = getInternalMaze(maze);
        assertEquals('.', after[3][2]);
    }

    @Test
    void testDeadEndsHandledCorrectly() {
        char[][] grid = mazeOf(
                "#####",
                "#S  #",
                "# ###",
                "# T #",
                "#####"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 1);

        assertEquals(4, result);
        char[][] after = getInternalMaze(maze);
        assertFalse(containsChar(after, 's'));
        assertTrue(containsChar(after, '.'));
    }

    @Test
    void testLargeMazeUniquePathSolvable() {
        char[][] grid = mazeOf(
                "###############",
                "#S    #########",
                "###   #########",
                "##### ###     #",
                "##### ### ### #",
                "#####     ### #",
                "######## #### #",
                "######## #### #",
                "############# #",
                "###########   #",
                "############# #",
                "#######       #",
                "#####   #######",
                "#######      T#",
                "###############"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 1);

        assertEquals(41, result);
        char[][] after = getInternalMaze(maze);
        assertTrue(containsChar(after, '.'));
        assertFalse(containsChar(after, 's'));
    }

    @Test
    void testLargeMazeUnsolvable() {
        char[][] grid = mazeOf(
                "###############",
                "#S            #",
                "############# #",
                "#             #",
                "# #############",
                "#             #",
                "############# #",
                "#             #",
                "###############",
                "#             #",
                "############# #",
                "#             #",
                "# #############",
                "#            T#",
                "###############"
        );

        Maze maze = new Maze(grid);
        int result = maze.escape(1, 1);

        assertEquals(0, result);
        char[][] after = getInternalMaze(maze);
        assertTrue(containsChar(after, 'x'));
        assertFalse(containsChar(after, 's'));
    }
}

