final int GRID_SIZE_X = 10, GRID_SIZE_Y = 10;


void main() throws InterruptedException {
    boolean[][] currentGrid = new boolean[GRID_SIZE_Y][GRID_SIZE_X];
    fillGridRandomly(currentGrid);

    boolean[][] nextGrid = new boolean[GRID_SIZE_Y][GRID_SIZE_X];

    int maxGenerations = 50;
    int generationCounter = 0;

    for (int generation = 0; generation < maxGenerations; generation++) {
        print(currentGrid);
        System.out.println("Generation: " + generationCounter++);
        System.out.println();

        getNextGeneration(currentGrid, nextGrid);

        boolean[][] temp = currentGrid;
        currentGrid = nextGrid;
        nextGrid = temp;

        Thread.sleep(500);
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

            if (dr == 0 && dc == 0)
                continue;

            int neighbourRow = row + dr;
            int neighbourCol = col + dc;

            if (neighbourRow >= 0 && neighbourRow < grid.length &&
                    neighbourCol >= 0 && neighbourCol < grid[0].length) {

                if (grid[neighbourRow][neighbourCol])
                    count++;
            }
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