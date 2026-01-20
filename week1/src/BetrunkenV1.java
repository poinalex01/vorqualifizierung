void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Wie viel Bier hast du getrunken?");
    int beer = scanner.nextInt();
    System.out.println("Wie viel Shots hast du getrunken?");
    int shots = scanner.nextInt();
    int drinks = beer + shots;

    if (drinks == 0) {
        System.out.println("gar nicht betrunken");
    } else if (drinks <= 2) {
        System.out.println("leicht betrunken");
    } else if (drinks <= 6 && shots == 0) {
        System.out.println("betrunken");
    } else {
        System.out.println("hoffnungslos betrunken");
    }
}