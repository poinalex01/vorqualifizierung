import java.util.Scanner;

public class TaschenrechnerV42_Einfach {
    static String OPERATIONS = "+-*/";

    static void main() {
        Scanner scanner = new Scanner(System.in);
        MyStack stack = new MyStack();

        while (true) {
            System.out.println("Stack: " + stack);
            System.out.print("↵ ");

            String input = scanner.nextLine();
            if (input.matches("\\d+(\\.\\d+)?")) {
                double number = Double.parseDouble(input);
                stack.push(number);
            } else if (OPERATIONS.contains(input)) {
                double num1 = stack.pop();
                double num2 = stack.pop();

                switch (input) {
                    case "+" -> stack.push(num2 + num1);
                    case "-" -> stack.push(num2 - num1);
                    case "*" -> stack.push(num2 * num1);
                    case "/" -> stack.push(num2 / num1);
                }
            }
        }
    }
}