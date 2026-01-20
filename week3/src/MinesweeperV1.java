// https://ds2.at/lms/mod/assign/view.php?id=76
final String[] POSSIBLE_STATES = {"0", "-1", "-2", "-3"};
final int GRID_SIZE = 10;

void main() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    String[][] map = new String[GRID_SIZE][GRID_SIZE];
    boolean[][] checkedMap = new boolean[GRID_SIZE][GRID_SIZE];
    int mineCount = 0; // wie oft kommt 0 vor
    boolean gameOver = false;
    String endMessage = "Das... war eine Mine. Du hast leider verloren.";


    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
            var newChar = POSSIBLE_STATES[random.nextInt(POSSIBLE_STATES.length)];
            if (newChar.equals("0")) {
                mineCount++;
            }
            map[i][j] = newChar;
        }
    }

    int rounds = 1;
    int possibleFields = GRID_SIZE * GRID_SIZE;
    int safeFields = possibleFields - mineCount;
    do {
        printGrid(map, checkedMap);

        double progress = (double) (rounds - 1) * 100 / safeFields;
        System.out.printf("\nDu hast %d/%d (%.2f %%) des nicht verminten Gebiets auf Minen gecheckt", rounds - 1, safeFields, progress);
        System.out.printf("\nEs bleiben noch %d Minen versteckt.", mineCount);

        System.out.println("\nWo willst du nach Minen suchen?");
        String wantedCoordinate = scanner.nextLine();

        int y = Character.toUpperCase(wantedCoordinate.charAt(0)) - 'A';
        int x = Integer.parseInt(String.valueOf(wantedCoordinate.charAt(1)));
        System.out.printf("User input: %d/%d", x, y);

        String updatedSymbole = "-";
        if (map[x][y].equals("0")) {
            updatedSymbole = "*";
            gameOver = true;
        }
        map[x][y] = updatedSymbole;
        checkedMap[x][y] = true;

        rounds++;
    } while (!gameOver);

    System.out.println(endMessage);
}

private void printGrid(String[][] map, boolean[][] checkedGrid) {
    char c = 'A';
    System.out.println();
    StringBuilder x = new StringBuilder("\t" + " ");
    for (int i = 0; i < map.length; i++) {
        x.append(c).append("  ");
        c++;
    }
    x.append("\n");
    System.out.println(x);

    for (int i = 0; i < map.length; i++) {
        StringBuilder currentLine = new StringBuilder();
        currentLine.append(i).append("\t");
        for (int j = 0; j < map.length; j++) {
            String symbole = " ";
            if (checkedGrid[i][j]) {
                symbole = map[i][j];
            }

            currentLine.append("[").append(symbole).append("]");
        }
        System.out.println(currentLine);
    }
}
