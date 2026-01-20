void main() {
    Scanner scanner = new Scanner(System.in);
    Random r = new Random();

    boolean isSatisfied;
    int sum = 0;
    int ctrHead = 0;
    int ctrTail = 0;

    do {
        int rounds;

        do {
            System.out.println("Wie oft soll die Münze geworfen werden (INSGESAMT ungerade, neue Zahl 1-10)?: ");
            rounds = scanner.nextInt();
        } while (rounds < 1 || rounds > 10 || (sum + rounds) % 2 == 0);

        sum += rounds;

        for (int i = 0; i < rounds; i++) {
            boolean isHead = r.nextBoolean();
            if (isHead) {
                ctrHead++;
                System.out.println("Werfe Münze… Kopf!");
            } else {
                ctrTail++;
                System.out.println("Werfe Münze… Zahl!");
            }
        }

        System.out.println("Summe Kopf: " + ctrHead);
        System.out.println("Summe Zahl: " + ctrTail);
        System.out.println("Bist du mit dem Ergebnis zufrieden?");
        isSatisfied = scanner.nextBoolean();

    } while (!isSatisfied);

    if (ctrHead > ctrTail) {
        System.out.println("Ich bekomme das Ticket.");
    } else {
        System.out.println("Stefan bekommt das Ticket.");
    }
}