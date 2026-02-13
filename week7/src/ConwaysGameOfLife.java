void main() throws InterruptedException {
    /*
    boolean[][] currentGrid = new boolean[GRID_SIZE_Y][GRID_SIZE_X];
    fillGridRandomly(currentGrid);
    */
    boolean[][] currentGrid = getTestPattern(3);

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

        Thread.sleep(750);
    }
}


/**
 * fills grid with a random living or dead cells
 * they get a chance to start alive based on the given probability.
 *
 * @param grid boolean array representing cells
 */
void fillGridRandomly(boolean[][] grid) {
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = Math.random() < .3;
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
int countNeighbours(boolean[][] grid, int row, int col) {
    int count = 0;

    /*
        (-1,-1)  (-1,0)  (-1,1)
        ( 0,-1)   (0,0)  ( 0,1)
        ( 1,-1)   (1,0)  ( 1,1)
    */
    for (int dr = -1; dr <= 1; dr++) {
        for (int dc = -1; dc <= 1; dc++) {

            if (dr == 0 && dc == 0) continue;

            /*
            int neighbourRow = row + dr;
            int neighbourCol = col + dc;

            if (neighbourRow >= 0 && neighbourRow < grid.length &&
                    neighbourCol >= 0 && neighbourCol < grid[0].length) {

                if (grid[neighbourRow][neighbourCol])
                    count++;
            }
            */

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
void getNextGeneration(boolean[][] current, boolean[][] next) {
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
void print(boolean[][] grid) {
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
boolean isAllDead(boolean[][] grid) {
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
    }

    System.out.println("invalid pattern index");
    return new boolean[][]{};
}