Scanner scanner = new Scanner(System.in);
Random random = new Random();
final int TRY_AMOUNT = 9;

void main() {
    int currentLevel;
    do {
        do {
            System.out.println("Welches Level willst du spielen? (1, 2, 3 || 0 zum abbrechen.)");
            currentLevel = scanner.nextInt();
            if (currentLevel < 0 || currentLevel > 3) {
                System.out.println("Ungueltige Eingabe!");
            }
        } while (currentLevel < 0 || currentLevel > 3);

        switch (currentLevel) {
            case 1 -> levelOne();
            case 2 -> levelTwo();
            case 3 -> levelThree();
        }

    } while (currentLevel != 0);
}


private void levelOne() {
    int randomNumber = random.nextInt(101);
    int tries = TRY_AMOUNT;

    do {
        System.out.println("Du hast noch " + tries + " Versuche!");

        int input;
        do {
            System.out.println("Gib deine Zahl ein:");
            input = scanner.nextInt();
            if (input < 0 || input > 100) {
                System.out.println("Die Zahl kann nur im Bereich von 0 bis 100 sein!");
            }
        } while (input < 0 || input > 100);

        if (input > randomNumber) {
            System.out.println("Kleiner");
        } else if (input < randomNumber) {
            System.out.println("Größer");
        } else {
            System.out.println("Erraten");
            return;
        }

        tries--;
    } while (tries > 0);
    System.out.println("Du hast verloren!");
}

private void levelTwo() {
    int randomNumber = random.nextInt(101);
    int tries = TRY_AMOUNT;

    do {
        System.out.println("Du hast noch " + tries + " Versuche!");

        int input;
        do {
            System.out.println("Gib deine Zahl ein:");
            input = scanner.nextInt();
            if (input < 0 || input > 100) {
                System.out.println("Die Zahl kann nur im Bereich von 0 bis 100 sein!");
            }
        } while (input < 0 || input > 100);

        int difference = Math.abs(input - randomNumber);
        if (input == randomNumber) {
            System.out.println("Erraten");
            return;
        } else if (difference <= 3) {
            //} else if (input > randomNumber - 3 && input < randomNumber + 3) {
            System.out.println("„fast da“: zwischen 1 und 3 daneben.");
        } else if (difference <= 10) {
            System.out.println("„relativ nahe“: zwischen 4 und 10 daneben");
        } else if (difference <= 20) {
            System.out.println("„Nicht ganz so weit weg“: zwischen 11 und 20 daneben");
        } else {
            System.out.println("„Weit weg“: mehr als 20 daneben");
        }

        tries--;
    } while (tries > 0);
    System.out.println("Du hast verloren!");
}

private void levelThree() {
    ArrayList<Integer> triedNumbers = new ArrayList<>();
    int randomNumber = random.nextInt(101);
    boolean aiTurn = random.nextBoolean();
    int tries = TRY_AMOUNT;
    int min = 0, max = 100;

    do {
        int input;
        if (aiTurn) {
            input = (max + min) >> 1;
            System.out.println("Die KI wählt: " + input);
        } else {
            System.out.println("Du hast noch " + tries + " Versuche!");

            do {
                System.out.println("Gib deine Zahl ein:");
                input = scanner.nextInt();
                if (input < 0 || input > 100) {
                    System.out.println("Die Zahl kann nur im Bereich von 0 bis 100 sein!");
                }
            } while (input < 0 || input > 100);

            tries--;
        }

        if (input > randomNumber) {
            max = Math.min(input - 1, max);
            System.out.println("Kleiner");
        } else if (input < randomNumber) {
            min = Math.max(input + 1, min);
            System.out.println("Größer");
        } else {
            System.out.println("Erraten");
            return;
        }

        triedNumbers.add(input);
        System.out.println(triedNumbers);
        aiTurn = !aiTurn;
    } while (tries > 0);
    System.out.println("Du hast verloren!");
}