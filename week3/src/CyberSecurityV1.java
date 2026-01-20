import java.util.Random;

final int MIN_LENGTH = 4;
final int PASSWORD_AMOUNT = 3;
final char[] AVAILABLE_CHARS = "ABCabc012!".toCharArray();

void main() {
    Random random = new Random();
    int bestCase = Integer.MAX_VALUE, worstCase = 0;
    long totalTries = 0;

    for (int i = 0; i < PASSWORD_AMOUNT; i++) {
        StringBuilder password = new StringBuilder();
        for (int j = 0; j < MIN_LENGTH; j++) {
            int randomIndex = random.nextInt(AVAILABLE_CHARS.length);
            password.append(AVAILABLE_CHARS[randomIndex]);
        }
        System.out.println("password: " + password);
        System.out.println("-".repeat(64));

        int ctr = 0;
        Loop:
        for (char a : AVAILABLE_CHARS) {
            for (char b : AVAILABLE_CHARS) {
                for (char c : AVAILABLE_CHARS) {
                    for (char d : AVAILABLE_CHARS) {
                        String guess = "" + a + b + c + d;
                        System.out.println(guess);
                        ctr++;

                        if (guess.contentEquals(password)) {
                            System.out.println("Found it: " + guess + " == " + password);
                            System.out.println("Took " + ctr + " guesses");

                            if (ctr < bestCase) bestCase = ctr;
                            if (ctr > worstCase) worstCase = ctr;

                            totalTries += ctr;
                            break Loop;
                        }
                    }
                }
            }
        }

        System.out.printf("On average took %d guesses each time. Best case was %d guesses, worst case was %d guesses.",
                totalTries / PASSWORD_AMOUNT, bestCase, worstCase);
    }
}

/*
    Wie oft musst du im besten Fall raten, um es zu knacken?
    1
    Wie oft im allerschlechtesten?
    10 ^ 4 = 10.000
    Wie oft wirst du im Durchschnitt probieren müssen?
    10.000 /2 = 5000
*/