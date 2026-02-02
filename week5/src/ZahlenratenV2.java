import java.awt.*;

static final int LENGTH = 101;
static final int TRY_AMOUNT = 9;

void main() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int secret = random.nextInt(100);
    secret = 63;
    boolean aiTurn = random.nextBoolean();
    aiTurn = false;

    ArrayList<Integer> numbers = new ArrayList<>();
    for (int i = 0; i <= LENGTH; i++)
        numbers.add(i);

    int guess;
    ArrayList<Integer> guessList = new ArrayList<>();
    int tries = TRY_AMOUNT;
    do {
        if (aiTurn) {
            if (!guessList.isEmpty()) {
                int bestGuess = numbers.get(numbers.size() / 2);
                double maxMinDistance = 0;

                for (int n : numbers) {
                    double minDistance = LENGTH;
                    for (int prevGuess : guessList) {
                        minDistance = Math.min(minDistance, Math.abs(n - prevGuess));
                    }
                    if (minDistance > maxMinDistance) {
                        maxMinDistance = minDistance;
                        bestGuess = n;
                    }
                }
                guess = bestGuess;
            } else {
                guess = LENGTH >> 1;
            }
            System.out.println("Die KI wählt: " + guess);
        } else {
            System.out.println("Du hast noch " + tries + " Versuche!");

            do {
                System.out.println("Gib deine Zahl ein:");
                guess = scanner.nextInt();
                if (guess < 0 || guess > 100) {
                    System.out.println("Die Zahl kann nur im Bereich von 0 bis 100 sein!");
                }
            } while (guess < 0 || guess > 100);

            tries--;
        }
        guessList.add(guess);
        System.out.println("secret:" + secret);

        int difference = Math.abs(guess - secret);
        String feedbackText;
        if (guess == secret) {
            System.out.println("Erraten");
            return;
        } else if (difference <= 3) {
            feedbackText = "„fast da“: zwischen 1 und 3 daneben.";
            removeRange(numbers, 0, Math.max(0, guess - 4));
            removeRange(numbers, Math.min(LENGTH, guess + 4), LENGTH);
        } else if (difference <= 10) {
            feedbackText = "„relativ nahe“: zwischen 4 und 10 daneben";
            removeRange(numbers, Math.max(0, guess - 3), Math.min(LENGTH, guess + 3)); // Changed guess + 4 to guess + 3
            removeRange(numbers, 0, Math.max(0, guess - 11));
            removeRange(numbers, Math.min(LENGTH, guess + 11), LENGTH);
        } else if (difference <= 20) {
            feedbackText = "„Nicht ganz so weit weg“: zwischen 11 und 20 daneben";
            removeRange(numbers, Math.max(0, guess - 10), Math.min(LENGTH, guess + 10));
            removeRange(numbers, 0, Math.max(0, guess - 21)); // Changed guess - 20 to guess - 21
            removeRange(numbers, Math.min(LENGTH, guess + 21), LENGTH); // Changed guess + 20 to guess + 21
        } else {
            feedbackText = "„Weit weg“: mehr als 20 daneben";
            removeRange(numbers, Math.max(0, guess - 21), Math.min(LENGTH, guess + 21)); // Changed guess - 20 and guess + 20 to guess - 21 and guess + 21
        }

        numbers.remove(Integer.valueOf(guess));
        System.out.println("Verbleibende Elemente: " + numbers);

        aiTurn = !aiTurn;
        /*
        System.out.printf(
                "Secret: %d, Guess: %d (Feedback: %d ... %d), verbleibende Elemente: %s%n",
                secret,
                guess,
                feedback[0],
                feedback[1],
                numbers
        );
         */

        System.out.println(feedbackText);
        System.out.println("Guesses: " + guessList);
        print(numbers);
    } while (tries > 0);
}
/*
    Beispiel: Secret: 54, Guess: 70 (Feedback: 11 ... 20), verbleibende Elemente: [50,51, ..., 59, 81, 82, ..., 90] wird visualisiert durch:

    ..................................................**********.....................**********..........
 */


/**
 * Removes all elements from the given ArrayList that fall within the inclusive range [lower, upper].
 * <p>
 * This method iterates through the list and removes all matching elements. If multiple elements
 * in the list fall within the specified range, all will be removed.
 * </p>
 *
 * @param al    the ArrayList of integers to process
 * @param lower the lower bound of the range (inclusive)
 * @param upper the upper bound of the range (inclusive)
 */
static void removeRange(ArrayList<Integer> al, int lower, int upper) {
    al.removeIf(n -> n >= lower && n <= upper);
}

/**
 * Prints a visual representation of which integers from 0 to 100 are present in the given ArrayList.
 *
 * @param al the ArrayList of integers to visualize
 */
static void print(ArrayList<Integer> al) {
    StringBuilder sb = new StringBuilder();
    int currentIndex = 0;

    for (int i = 0; i < LENGTH; i++) {
        if (currentIndex < al.size() && al.get(currentIndex) == i) {
            sb.append("*");
            currentIndex++;
        } else {
            sb.append(".");
        }
    }

    System.out.println(sb);
}