void main() {
    Scanner scanner = new Scanner(System.in);

    int rounds;
    do {
        System.out.println("Wie oft soll die Münze geworfen werden (ungerade Zahl 1-10)?: ");
        rounds = scanner.nextInt();
    } while (rounds < 1 || rounds > 10 || rounds % 2 == 0);

    Random r = new Random();
    int ctrHead = 0, ctrTail = 0;
    for (int i = 0; i < rounds; i++) {
        boolean isHead = r.nextBoolean();
        if (isHead) {
            ctrHead++;
            System.out.println("Werfe Münze… Kopf!");
        }
        else {
            ctrTail++;
            System.out.println("Werfe Münze… Zahl!");
        }
    }

    System.out.println("Summe Kopf: " + ctrHead + "\nSumme Zahl: " + ctrTail);
    if (ctrHead > ctrTail) {
        System.out.println("Ich bekomme das Ticket.");
    } else  {
        System.out.println("Stefan bekommt das Ticket.");
    }
}