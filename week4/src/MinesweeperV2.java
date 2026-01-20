// https://ds2.at/lms/mod/assign/view.php?id=76
final int[] POSSIBLE_STATES = {0, -1, -2, -3};
final int GRID_SIZE = 10;

void main() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random(0);

    int[][] map = new int[GRID_SIZE][GRID_SIZE];
    int mineCount = 0; // wie oft kommt 0 vor
    boolean gameOver = false;

    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
            int newChar = POSSIBLE_STATES[random.nextInt(POSSIBLE_STATES.length)];
            if (newChar == 0) {
                mineCount++;
            }
            map[i][j] = newChar;
        }
    }

    int rounds = 1;
    int possibleFields = GRID_SIZE * GRID_SIZE;
    int safeFields = possibleFields - mineCount;
    do {
        print(map, rounds, safeFields, mineCount);

       /* if (safeFields) {
            System.out.println("Du hast gewonnen!");
        }
        */

        String wantedCoordinate;
        Pattern pattern = Pattern.compile("[A-J][0-9]");
        do {
            System.out.println("\nWo willst du nach Minen suchen?");
            wantedCoordinate = scanner.nextLine();
            if (!pattern.matcher(wantedCoordinate).find() || wantedCoordinate.length() != 2) {
                System.out.println("Ungültige Eingabe!");
            }
        } while (!pattern.matcher(wantedCoordinate).find() || wantedCoordinate.length() != 2);


        int y = wantedCoordinate.charAt(0) - 'A';
        int x = wantedCoordinate.charAt(1) - '0';
        System.out.printf("User input: %d/%d\n", x, y);

        if (map[x][y] == 0) {
            map[x][y] = 100;
            gameOver = true;
            print(map, rounds, safeFields, mineCount);
        } else {
            int originalVal = map[x][y];
            int val = Math.abs(originalVal);
            int radius = val - 1;
            int newlyOpened = 0;

            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    int checkX = x + i;
                    int checkY = y + j;

                    if (checkX >= 0 && checkX < GRID_SIZE && checkY >= 0 && checkY < GRID_SIZE) {
                        if (map[checkX][checkY] == 0) {
                            map[checkX][checkY] = 100;
                            mineCount--;
                        } else if (map[checkX][checkY] < 0) {
                            map[checkX][checkY] = Math.abs(map[checkX][checkY]);
                            newlyOpened++;
                        }
                    }
                }
            }
            System.out.printf("\nVAL: %d-> %d", originalVal, newlyOpened);
            rounds += newlyOpened;
        }
    } while (!gameOver);

    System.out.println("\nDas... war eine Mine. Du hast leider verloren.");
}

private void print(int[][] map, int rounds, int safeFields, int mineCount) {
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
        for (int j = 0; j < map[i].length; j++) {
            String symbole = " ";

            if (map[i][j] == 100) {
                symbole = "*";
            } else if (map[i][j] > 0) {
                symbole = "-";
            }

            currentLine.append("[").append(symbole).append("]");
        }
        System.out.println(currentLine);
    }

    double progress = (double) (rounds - 1) * 100 / safeFields;
    System.out.printf("\nDu hast %d/%d (%.2f %%) des nicht verminten Gebiets auf Minen gecheckt", rounds - 1, safeFields, progress);
    System.out.printf("\nEs bleiben noch %d Minen versteckt.", mineCount);
}
