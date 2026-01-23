


void main() {
    // int[] guess = readUserInput();
    // int[] secret = createSecret();
    int[] guess = {2, 2, 1, 3}; // +
    int[] secret = {1, 4, 5, 1};

    int[] points;
    points = evaluate(new int[]{4, 2, 2, 5}, secret); // ++
    System.out.println("points: " + Arrays.toString(points));
    points = evaluate(new int[]{1, 5, 4, 1}, secret); // ##++
    System.out.println("points: " + Arrays.toString(points));
    points = evaluate(new int[]{5, 1, 4, 1}, secret); // #+++
    System.out.println("points: " + Arrays.toString(points));
    points = evaluate(new int[]{1, 4, 5, 1}, secret); // ####
    System.out.println("points: " + Arrays.toString(points));
    // int[] points = evaluate(guess,secret);
    System.out.println("points: " + Arrays.toString(points));

    int[][] map = new int[8][4];
    for (int[] row : map) {
        Arrays.fill(row, -1);
    }

    // print(map);
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

    for (int i = 0; i < guess.length; i++) {
        int tempPositionCtr= 0, tempColorCtr = 0;

        for (int j = 0; j < secret.length; j++) {
            if (guess[i] == secret[i]) {// if cgd an der selben stelle rightPositionCtr++
                tempPositionCtr++;
                break;
            } else if (guess[i] == ) { // alle anderen indexes von secret durchgehen und wenn vorhanden rightColorCtr++

            }
        }

        rightPositionCtr = tempPositionCtr;
        rightColorCtr = tempColorCtr;
    }




/*
    for (int i = 0; i < guess.length; i++) {
        int currentGuess = guess[i];

        for (int j = 0; j < secret.length; j++) {
            int currentSecret = secret[j];

            if (currentGuess == currentSecret) {
                rightPositionCtr++;
                break;
            } else {
                // check every other digit in secret for currentGuess
                for (int k : secret) {
                    if (i != j) {
                        if (currentGuess == k) {
                            rightColorCtr++;
                        }
                    }
                }
            }
        }
    }
 */
    return new int[]{rightPositionCtr, rightColorCtr};
}


/**
 * Creates a secret combination for the MasterMind game.
 * The secret consists of 4 random integers between 1 and 6 (inclusive).
 *
 * @return An array of integers representing the secret combination.
 */
static int[] createSecret() {
    return new Random().ints(4, 1, 7).toArray();
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
    System.out.println("Enter a 4-digit number between 1 and 6 (e.g \"1234\", \"3456\")");

    int[] inputArray = new int[4];
    String input;
    boolean isValid = false;
    do {
        input = scanner.nextLine();
        if (input.length() != 4) {
            System.out.println("Invalid input length!");
        } else {
            char[] charArray = input.toCharArray();

            for (int i = 0; i < charArray.length; i++) {
                char currentChar = charArray[i];
                int currentNumber = Integer.parseInt(String.valueOf(currentChar));

                if (currentNumber <= 0 || currentNumber >= 7) {
                    System.out.println("Invalid input!");
                    break;
                } else {
                    inputArray[i] = currentNumber;
                    if (i == 3) isValid = true;
                }
            }
        }
    } while (!isValid);

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
        System.out.print("+---".repeat(row.length) + "+\n");

        for (int digit : row) {
            if (digit == -1) {
                System.out.print("|   ");
            } else {
                System.out.print("| " + digit + " ");
            }
        }

        System.out.println("|");
    }

    System.out.print("+---".repeat(map[0].length) + "+\n");
}