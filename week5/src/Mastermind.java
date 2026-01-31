final int ROUNDS = 8;

void main() {
    int[][] map = new int[8][6];
    for (int[] ints : map) {
        Arrays.fill(ints, -1);
    }

    int[] secret = createSecret();

    for (int round = 0; round < ROUNDS; round++) {
        int[] guess = readUserInput();
        int[] evaluated = evaluate(guess, secret); // 0= #, 1= +

        System.arraycopy(guess, 0, map[round], 0, 4);
        map[round][4] = evaluated[0]; // #
        map[round][5] = evaluated[1]; // w+

        print(map);

        if (evaluated[0] == 4) {
            System.out.println("Gewonnen!");
            return;
        }
    }

    System.out.println("Leider verloren!");
    System.out.println("Lösung: " + Arrays.toString(secret));
}

/**
 * Evaluates a guess against the secret combination in the MasterMind game.
 * It counts how many numbers are correct and in the correct position (black pegs)
 * and how many numbers are correct but in the wrong position (white pegs).
 * The guess and secret are both arrays of integers of length 4,
 * where each integer is between 1 and 6 (inclusive).
 *
 * @param guess  The player's guess as an array of integers.
 * @param secret The secret combination as an array of integers.
 * @return An array where the first element is the count of correct positions
 * and the second element is the count of misplaced numbers.
 */
static int[] evaluate(int[] guess, int[] secret) {
    int rightPositionCtr = 0, rightColorCtr = 0;
    boolean[] usedGuess = new boolean[guess.length];
    boolean[] usedSecret = new boolean[secret.length];

    for (int i = 0; i < guess.length; i++) { // black
        if (guess[i] == secret[i]) {
            rightPositionCtr++;
            usedGuess[i] = true;
            usedSecret[i] = true;
        }
    }

    for (int i = 0; i < guess.length; i++) { // white
        if (usedGuess[i]) continue;

        int currentGuess = guess[i];
        for (int j = 0; j < secret.length; j++) {
            if (usedSecret[j]) continue;

            int currentSecret = secret[j];
            if (currentGuess == currentSecret) {
                rightColorCtr++;
                usedSecret[j] = true;
                break;
            }
        }
    }

    return new int[]{rightPositionCtr, rightColorCtr};
}


/**
 * Creates a secret combination for the MasterMind game.
 * The secret consists of 4 random integers between 1 and 6 (inclusive).
 *
 * @return An array of integers representing the secret combination.
 */
static int[] createSecret() {
    return new Random(0).ints(4, 1, 7).toArray();
}


/**
 * Reads user input for a guess in the MasterMind game.
 * The input must be a 4-digit number with each digit between 1 and 6.
 * If the input is invalid, it prompts the user to try again.
 * Example valid input: "1234", "4561", etc.
 *
 * @return An array of integers representing the user's guess.
 * The array contains 4 integers, each between 1 and 6.
 */
static int[] readUserInput() {
    Scanner scanner = new Scanner(System.in);
    int[] inputArray = new int[4];
    String input;

    do {
        System.out.println("Enter a 4-digit number between 1 and 6 (e.g \"1234\", \"3456\")");
        input = scanner.next();

        if (!input.matches("[1-6]{4}")) {
            System.out.println("Invalid Input!");
            continue;
        }

        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char currentChar = charArray[i];
            inputArray[i] = Integer.parseInt(String.valueOf(currentChar));
        }
    } while (!input.matches("[1-6]{4}"));

    return inputArray;
}

/**
 * Prints the current state of the game board.
 * Each row represents a guess with feedback on correct and misplaced numbers.
 *
 * @param map The game board represented as a 2D array.
 *            Each row contains the guess and feedback:
 *            column 0-3: guessed numbers,
 *            column 4: correct positions (black pegs),
 *            column 5: misplaced numbers (white pegs).
 *            The first column is empty (0) if no guess was made.
 */
static void print(int[][] map) {
    for (int[] row : map) {
        System.out.print("+---".repeat(row.length - 2) + "+------" + "+\n");

        int spaces = 6;
        for (int j = 0; j < row.length; j++) {
            if (row[j] == -1) {
                if (j == row.length - 1) {
                    System.out.print("   ");
                } else {
                    System.out.print("|   ");
                }
            } else {
                boolean isPenultimate = j == row.length - 2;
                boolean isLast = j == row.length - 1;

                if (isPenultimate || isLast) {
                    char symbol = isLast ? '+' : '#';
                    String output = String.valueOf(symbol).repeat(row[j]);
                    if (!isLast) output = "| " + output;
                    System.out.print(output);

                    spaces -= j;
                } else {
                    System.out.print("| " + row[j] + " ");
                }
            }
        }


        if (row[0] == -1) System.out.println("|");
        else System.out.println(" ".repeat(Math.abs(spaces)) + "|");
    }

    System.out.println("+---".repeat(map[0].length - 2) + "-------+");
}