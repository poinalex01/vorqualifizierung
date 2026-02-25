import java.util.Scanner;

public class TaschenrechnerV42_Erweitert {
    static String OPERATIONS = "+-*/";
    static Scanner scanner = new Scanner(System.in);
    static MyStack stack = new MyStack();

    static void main() {

        while (true) {
            System.out.println("Stack: " + stack);
            System.out.print("> ");

            String input = scanner.nextLine();
            try {
                if (input.matches("-?\\d+(\\.\\d+)?")) {
                    stack.push(Double.parseDouble(input));
                } else if (OPERATIONS.contains(input)) {
                    checkSize(2);

                    double num1 = stack.pop();
                    double num2 = stack.pop();

                    switch (input) {
                        case "+" -> stack.push(num2 + num1);
                        case "-" -> stack.push(num2 - num1);
                        case "*" -> stack.push(num2 * num1);
                        case "/" -> stack.push(num2 / num1);
                    }
                } else if (input.equals("sqrt")) {
                    checkSize(1);

                    double a = stack.pop();
                    stack.push(Math.sqrt(a));
                } else if (input.equals("inv")) {
                    checkSize(1);

                    double a = stack.pop();
                    stack.push(1.0 / a);
                } else if (input.equals("fact") || input.equals("!")) {
                    checkSize(1);

                    double num = stack.pop();

                    double result = 1;
                    for (int i = 1; i <= num; i++)
                        result *= i;

                    stack.push(result);
                } else if (input.equals("pi")) {
                    stack.push(Math.PI);
                } else if (input.equals("dup")) {
                    checkSize(1);

                    double num = stack.pop();
                    stack.push(num);
                    stack.push(num);
                } else if (input.equals("drop")) {
                    checkSize(1);

                    stack.pop();
                } else if (input.equals("swap")) {
                    checkSize(2);

                    double num1 = stack.pop();
                    double num2 = stack.pop();

                    stack.push(num1);
                    stack.push(num2);
                } else if (input.equals("clr")) {
                    stack.clear();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void checkSize(int n) {
        if (stack.size() < n)
            throw new RuntimeException("Zu wenige Werte im Stack!");
    }
}