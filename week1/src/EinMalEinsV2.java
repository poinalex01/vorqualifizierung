void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welche Reihe soll ausgegeben werden?");
    int row = scanner.nextInt();

    for (int i = 1; i < 11; i++) {
        System.out.println(i + " * " + row + " = " + (i * row));
    }
}