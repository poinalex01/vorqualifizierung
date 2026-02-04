void main() {
    int[][] card = generateCard();
    boolean isValid = verifyCard(card);
    System.out.println("isValid: " + isValid);
    if (isValid) {
        printCard(card);
        printCard2(card);
    }
}


static int[][] generateCard() {
    /*
    5 Zahlen für die B Spalte im Bereich 1 bis 15
    5 Zahlen für die I Spalte im Bereich 16 bis 30
    4 Zahlen für die N Spalte im Bereich 31 bis 45
    5 Zahlen für die G Spalte im Bereich 46 bis 60
    5 Zahlen für die O Spalte im Bereich 61 bis 75
    Das mittlere Feld wird als freies Feld gekennzeichnrt.
    * */
    Random random = new Random();
    int[][] card = new int[5][5];
    int[] ranges = {1, 16, 31, 46, 61};

    for (int col = 0; col < 5; col++) {
        int lower = ranges[col], upper = ranges[col] + 15;
        int[] numbers = random.ints(lower, upper).distinct().limit(5).toArray();

        int numIndex = 0;
        for (int row = 0; row < 5; row++) {
            if (col == 2 && row == 2)
                continue;
            card[row][col] = numbers[numIndex++];
        }
    }

    card[2][2] = 0;
    return card;
}

static void printCard(int[][] card) {
    for (int[] row : card) {
        for (int n : row) {
            if (n == 0)
                System.out.print("** ");
            else if (n < 10)
                System.out.print(" " + n + " ");
            else
                System.out.print(n + " ");
        }
        System.out.println();
    }
}

static void printCard2(int[][] card) {
    String title = "BINGO";
    System.out.println("+----".repeat(5) + "+");
    for (char c : title.toCharArray())
        System.out.print("|  " + c + " ");
    System.out.println("|");
    System.out.println("+----".repeat(5) + "+");

    for (int[] row : card) {
        for (int n : row) {
            if (n == 0) {
                System.out.print("| **");
            } else if (n < 10) {
                System.out.print("|  " + n);
            } else {
                System.out.print("| " + n);
            }
            System.out.print(" ");
        }
        System.out.println("|");
        System.out.println("+----".repeat(5) + "+");
    }
}

static boolean verifyCard(int[][] card) {
    if (card.length != 5)
        return false;

    for (int[] row : card) {
        if (row == null || row.length != 5)
            return false;
    }

    if (card[2][2] != 0)
        return false;

    Set<Integer> uniqueNumbers = new HashSet<>();
    for (int[] row : card) {
        for (int n : row) {
            if (n != 0 && !uniqueNumbers.add(n))
                return false;
        }
    }

    return true;
}