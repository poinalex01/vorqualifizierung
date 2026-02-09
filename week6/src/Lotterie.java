void main() {
    ArrayList<Integer> invalidSerials = new ArrayList<>();
    int validTips = 0, invalidTips = 0;
    int threeHits = 0, fourHits = 0, fiveHits = 0, sixHits = 0;
    int[] drawnNumbers = createNumbers();

    File file = new File("week6/src/lotto10k.txt");
    try (Scanner myReader = new Scanner(file)) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] values = data.split(";");
            if (validate(data)) {
                validTips++;
            } else {
                invalidSerials.add(Integer.parseInt(values[0]));
                invalidTips++;
            }

            int[] tip = new int[6];
            for (int i = 0; i < 6; i++)
                tip[i] = Integer.parseInt(values[i + 1]);

            int hits = countEquals(tip, drawnNumbers);

            if (hits == 3) threeHits++;
            else if (hits == 4) fourHits++;
            else if (hits == 5) fiveHits++;
            else if (hits == 6) sixHits++;
        }
    } catch (FileNotFoundException e) {
        System.out.println("Couldn't find the file: " + file.getPath());
        e.printStackTrace();
    }


    System.out.println("Gültige Tipps: " + validTips);
    System.out.println("Ungültige Tipps: " + invalidTips);
    System.out.println();

    System.out.println("Gezogene Zahlen: " + Arrays.toString(drawnNumbers));
    System.out.println("--------------------------------------------------");
    System.out.println("3er:\t" + threeHits);
    System.out.println("4er:\t" + fourHits);
    System.out.println("5er:\t" + fiveHits);
    System.out.println("6er:\t" + sixHits);
    System.out.println("--------------------------------------------------");
    System.out.println("Ungültig: " + invalidSerials);
}


/**
 * Creates a random set of 6 unique sorted lotto numbers between 1 and 45.
 *
 * @return an sorted array containing the 6 unique lotto numbers
 */
static int[] createNumbers() {
    Random random = new Random();
    return random.ints(1, 45).distinct().limit(6).sorted().toArray();
}

/**
 * Counts the number of equal elements in two sorted arrays.
 *
 * @param a1 the first sorted array
 * @param a2 the second sorted array
 * @return the count of equal elements
 */
static int countEquals(int[] a1, int[] a2) {
    int ctr = 0;

    for (int i : a1) {
        for (int j : a2) {
            if (i == j) {
                ctr++;
            }
        }
    }

    return ctr;
}

/**
 * Checks if the checksum of the given string matches the last character.
 * The string should be formatted as "id;num1;num2;...;num6;checksum".
 *
 * @param s the string to validate
 * @return true if the checksum matches, false otherwise
 */
static boolean validate(String s) {
    char checksumChar = s.charAt(s.length() - 1);
    int givenChecksum = checksumChar - '0';

    int sum = 0;
    for (int i = 0; i < s.length() - 1; i++)
        sum += s.charAt(i);

    int calculatedChecksum = sum % 10;
    return calculatedChecksum == givenChecksum;
}
