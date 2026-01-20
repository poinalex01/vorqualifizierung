void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Geben Sie eine Zahl ein: ");
    int n = scanner.nextInt();

    if (n % 3 == 0 && n % 5 == 0) {
        System.out.println("FizzBuzz");
    } else if (n % 3 == 0) {
        System.out.println("Fizz");
    } else if (n % 5 == 0) {
        System.out.println("Buzz");
    } else {
        System.out.println(n);
    }
}