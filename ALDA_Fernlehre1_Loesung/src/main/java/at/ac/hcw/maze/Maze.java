package at.ac.hcw.maze;

public class Maze {
    private final char[][] maze;
    private final int rows;
    private final int cols;

    /**
     * Constructor for the maze.
     * @param initial matrix of chars containing the maze.
     */
    public Maze(char[][] initial) {
        if (initial == null || initial.length == 0 || initial[0].length == 0) {
            throw new IllegalArgumentException("Maze must not be empty");
        }
        this.rows = initial.length;
        this.cols = initial[0].length;
        this.maze = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            if (initial[r].length != cols) {
                throw new IllegalArgumentException("Maze must be rectangular");
            }
            System.arraycopy(initial[r], 0, this.maze[r], 0, cols);
        }
    }

    /**
     * Pretty prints the maze.
     */
    public void print() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(maze[r][c]);
                if (c < cols - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    /**
     * The recursive method responsible for solving the maze.
     * @param row the row to start from.
     * @param col the column to start from.
     * @return the number of steps taken to the target or 0 if there is no way to reach the target.
     */
    public int escape(int row, int col) {
        // Ausserhalb des Labyrinths kann kein Weg liegen.
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return 0;
        }

        char field = maze[row][col];

        // Waende, Sackgassen und der aktuelle Suchpfad sind gesperrt.
        if (field == '#' || field == 'x' || field == 's') {
            return 0;
        }

        // Das Ziel selbst zaehlt als letzter Schritt.
        if (field == 'T') {
            return 1;
        }

        // 's' markiert den aktuellen Pfad und verhindert Kreise.
        maze[row][col] = 's';

        // Feste Suchreihenfolge im Uhrzeigersinn: oben, rechts, unten, links.
        // Dadurch ist der "zuerst gefundene" Weg immer reproduzierbar.
        int steps = escape(row - 1, col);
        if (steps == 0) {
            steps = escape(row, col + 1);
        }
        if (steps == 0) {
            steps = escape(row + 1, col);
        }
        if (steps == 0) {
            steps = escape(row, col - 1);
        }

        if (steps > 0) {
            // Beim Zurueckkehren wird der gefundene Weg dauerhaft markiert.
            maze[row][col] = '.';
            return steps + 1;
        }

        // Von diesem Feld fuehrt kein Weg zum Ziel.
        maze[row][col] = 'x';
        return 0;
    }

    public static void main(String[] args) {
        char[][] sample = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', ' ', ' ', ' ', ' ', '#', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', ' ', '#', 'T', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        Maze maze = new Maze(sample);
        System.out.println("Schritte bis zum Ziel: " + maze.escape(1, 1));
        maze.print();
    }
}

