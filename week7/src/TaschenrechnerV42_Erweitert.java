import java.util.Scanner;

public class TaschenrechnerV42_Erweitert {
    static String OPERATIONS = "+-*/";
    static String NOT_ENOUGH_ITEMS_MESSAGE = "Zu wenige Werte im Stack!";

    static void main() {
        Scanner scanner = new Scanner(System.in);
        MyStack stack = new MyStack();

        while (true) {
            System.out.println("Stack: " + stack);
            System.out.print("> ");

            String input = scanner.nextLine();
            try {
                if (input.matches("\\d+(\\.\\d+)?")) {
                    stack.push(Double.parseDouble(input));
                } else if (OPERATIONS.contains(input)) {
                    if (stack.size() < 2) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double num1 = stack.pop();
                    double num2 = stack.pop();

                    switch (input) {
                        case "+" -> stack.push(num2 + num1);
                        case "-" -> stack.push(num2 - num1);
                        case "*" -> stack.push(num2 * num1);
                        case "/" -> stack.push(num2 / num1);
                    }
                } else if (input.equals("sqrt")) {
                    if (stack.size() < 1) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double a = stack.pop();
                    stack.push(Math.sqrt(a));
                } else if (input.equals("inv")) {
                    if (stack.size() < 1) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double a = stack.pop();
                    stack.push(1.0 / a);
                } else if (input.equals("fact") || input.equals("!")) {
                    if (stack.size() < 1) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double num = stack.pop();

                    double result = 1;
                    for (int i = 1; i <= num; i++)
                        result *= i;

                    stack.push(result);
                } else if (input.equals("pi")) {
                    stack.push(Math.PI);
                } else if (input.equals("dup")) {
                    if (stack.size() < 1) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double num = stack.pop();
                    stack.push(num);
                    stack.push(num);
                } else if (input.equals("drop")) {
                    if (stack.size() < 1) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    stack.pop();
                } else if (input.equals("swap")) {
                    if (stack.size() < 2) {
                        System.out.println(NOT_ENOUGH_ITEMS_MESSAGE);
                        continue;
                    }

                    double num1 = stack.pop();
                    double num2 = stack.pop();

                    stack.push(num1);
                    stack.push(num2);
                } else if (input.equals("clr")) {
                    stack.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}