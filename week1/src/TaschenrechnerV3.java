void main() {
    Scanner sc = new Scanner(System.in);

    char operator = ' ';
    while (operator != 'X') {
        do {
            System.out.println("Gib den Operator (+, -, *, /, %) ein:");
            operator = sc.next().charAt(0);
        } while (operator != '+' && operator != '-' && operator != '*' && operator != '/' && operator != '%' && operator != 'X');

        if (operator != 'X') {
            System.out.println("Gib die erste Zahl ein:");
            int a = sc.nextInt();
            System.out.println("Gib die zweite Zahl ein:");
            int b = sc.nextInt();

            switch (operator) {
                case '+':
                    System.out.println(a + " + " + b + " = " + (a + b));
                    break;
                case '-':
                    System.out.println(a + " - " + b + " = " + (a - b));
                    break;
                case '*':
                    System.out.println(a + " * " + b + " = " + (a * b));
                    break;
                case '/':
                    System.out.println(a + " / " + b + " = " + ((double) a / b));
                    break;
                case '%':
                    System.out.println(a + " % " + b + " = " + (a % b));
                    break;
            }
        }
    }
}