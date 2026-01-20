void main() {
    Scanner scanner = new Scanner(System.in);
    Random r = new Random();

    System.out.println("Wie oft soll die Münze geworfen werden?: ");
    int rounds = scanner.nextInt();

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
    } else if (ctrHead == ctrTail) {
        System.out.println("Gelichstand!");
    }else  {
        System.out.println("Stefan bekommt das Ticket.");
    }
}