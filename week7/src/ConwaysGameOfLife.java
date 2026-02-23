import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

class ConwaysGameOfLife extends JPanel {
    static int GRID_SIZE_Y = 50, GRID_SIZE_X = 50;
    boolean[][] grid;
    static int cellSize = 10;

    public ConwaysGameOfLife(boolean[][] grid) {
        this.grid = grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col]) g.setColor(Color.GREEN);
                else g.setColor(Color.BLACK);

                g.fillRect(col * cellSize, row * cellSize, getWidth(), getHeight());
                g.drawRect(col * cellSize, row * cellSize, getWidth(), getHeight());


                g.setColor(Color.black);
                g.drawRect(col * cellSize, row * cellSize, getWidth(), getHeight());
            }
        }
    }

    static void main() throws InterruptedException {
        boolean[][] currentGrid = new boolean[GRID_SIZE_Y][GRID_SIZE_X];
        fillGridRandomly(currentGrid);

        // boolean[][] currentGrid = getTestPattern(5);

        ConwaysGameOfLife panel = new ConwaysGameOfLife(currentGrid);

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setName("Game of Life");
        frame.setSize(currentGrid[0].length * cellSize, currentGrid.length * cellSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boolean[][] nextGrid = new boolean[currentGrid.length][currentGrid[0].length];
        Set<String> history = new HashSet<>();

        int generationCounter = 0;

        while (true) {
            print(currentGrid);
            String state = getStateAsString(currentGrid);
            System.out.println("Generation: " + generationCounter++);
            System.out.println();

            if (history.contains(state)) {
                System.out.println("Pattern repeated");
                return;
            }
            history.add(state);

            if (isAllDead(currentGrid)) {
                System.out.println("Every cell died");
                return;
            }

            getNextGeneration(currentGrid, nextGrid);

            boolean[][] temp = currentGrid;
            currentGrid = nextGrid;
            nextGrid = temp;

            panel.setGrid(currentGrid);
            Thread.sleep(100);
        }
    }


    /**
     * fills grid with a random living or dead cells
     * they get a chance to start alive based on the given probability.
     *
     * @param grid boolean array representing cells
     */
    static void fillGridRandomly(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Math.random() < .1;
            }
        }
    }


    /**
     * counts how many alive neighbor cells are surrounding
     *
     * @param grid current map
     * @param row  row index
     * @param col  column index
     * @return num of living neighbors
     */
    static int countNeighbours(boolean[][] grid, int row, int col) {
        int count = 0;

    /*
        (-1,-1)  (-1,0)  (-1,1)
        ( 0,-1)   (0,0)  ( 0,1)
        ( 1,-1)   (1,0)  ( 1,1)
    */
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {

                if (dr == 0 && dc == 0) continue;

                int neighbourRow = (row + dr + grid.length) % grid.length;
                int neighbourCol = (col + dc + grid[0].length) % grid[0].length;

                if (grid[neighbourRow][neighbourCol]) count++;
            }
        }

        return count;
    }


    /**
     * creates the next state of ceels and saves it into 'next' array.
     * - dead cell with exactly 3 neighbors become alive
     * - living cell with 2 or 3 neighbors stays alive
     * - every other case dies or stays dead
     *
     * @param current current grid that doesnt change
     * @param next    next generation array
     */
    static void getNextGeneration(boolean[][] current, boolean[][] next) {
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {

                int neighbours = countNeighbours(current, i, j);

                if (current[i][j]) {
                    next[i][j] = (neighbours == 2 || neighbours == 3);
                } else {
                    next[i][j] = (neighbours == 3);
                }
            }
        }
    }

    /**
     * prints world into the console
     *
     * @param grid world array
     */
    static void print(boolean[][] grid) {
        for (boolean[] row : grid) {
            for (boolean isAlive : row) {
                if (isAlive) {
                    System.out.print("\t[]");
                } else {
                    System.out.print("\t__");
                }
            }
            System.out.println();
        }
    }

    /**
     * checks if all cells are dead
     *
     * @param grid world array
     * @return bool
     */
    static boolean isAllDead(boolean[][] grid) { // TODO: no more needed if bitmap
        for (boolean[] row : grid) {
            for (boolean isAlive : row) {
                if (isAlive) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * creates a string of 0 and 1 so we can remember every pattern that has ever been created
     *
     * @param grid world array
     * @return String that consists out of 0 and 1 to remember earlier patterns
     */
    static String getStateAsString(boolean[][] grid) {
        StringBuilder sb = new StringBuilder();

        for (boolean[] row : grid) {
            for (int c = 0; c < grid[0].length; c++) {
                sb.append(row[c] ? "1" : "0");
            }
        }

        return sb.toString();
    }

    /**
     * creates a string of 0 and 1 so we can remember every pattern that has ever been created
     *
     * @param wantedPattern to choose between n different patterns to test from
     * @return 2D boolean grid map for testing
     */
    private static boolean[][] getTestPattern(int wantedPattern) {
        switch (wantedPattern) {
            case 1 -> {
                return new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}}; // pattern repeats
            }

            case 2 -> {
                return new boolean[][]{{false, false, false, false, false}, {false, false, true, false, false}, {false, false, true, false, false}, {false, false, true, false, false}, {false, false, false, false, false}}; //   generation 0 == vertikal
                //      generation 1 == horizontal
                //      generation 2 == vertikal
                //      erkennt Wiederholung und  stoppt
            }

            case 3 -> {
                return new boolean[][]{{false, true, false, false, false}, {false, false, true, false, false}, {true, true, true, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}; // stops because of repeating after 20gen
            }

            case 4 -> {
                return new boolean[][]{{false, false, false}, {false, true, false}, {false, false, false}}; // everything dies first gen
            }

            case 5 -> {
                int rows = 60;
                int cols = 100;
                boolean[][] grid = new boolean[rows][cols];

                // ===== GLIDER 1 =====
                grid[1][2] = true;
                grid[2][3] = true;
                grid[3][1] = true;
                grid[3][2] = true;
                grid[3][3] = true;

                // ===== GLIDER 2 =====
                grid[10][20] = true;
                grid[11][21] = true;
                grid[12][19] = true;
                grid[12][20] = true;
                grid[12][21] = true;

                // ===== BLINKER (oscillator) =====
                grid[5][50] = true;
                grid[6][50] = true;
                grid[7][50] = true;

                // ===== TOAD (oscillator) =====
                grid[20][40] = true;
                grid[20][41] = true;
                grid[20][42] = true;
                grid[21][39] = true;
                grid[21][40] = true;
                grid[21][41] = true;

                // ===== BEACON (oscillator) =====
                grid[30][10] = true;
                grid[30][11] = true;
                grid[31][10] = true;
                grid[31][11] = true;

                grid[32][12] = true;
                grid[32][13] = true;
                grid[33][12] = true;
                grid[33][13] = true;

                // ===== BLOCK (still life) =====
                grid[15][70] = true;
                grid[15][71] = true;
                grid[16][70] = true;
                grid[16][71] = true;

                // ===== LIGHTWEIGHT SPACESHIP =====
                grid[40][50] = true;
                grid[40][53] = true;
                grid[41][54] = true;
                grid[42][50] = true;
                grid[42][54] = true;
                grid[43][51] = true;
                grid[43][52] = true;
                grid[43][53] = true;
                grid[43][54] = true;

                // ===== RANDOM CLUSTER =====
                grid[50][80] = true;
                grid[50][81] = true;
                grid[51][79] = true;
                grid[51][82] = true;
                grid[52][80] = true;
                grid[52][81] = true;

                return grid;
            }
        }

        System.out.println("invalid pattern index");
        return new boolean[][]{};
    }
}