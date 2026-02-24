import java.util.Scanner;

public class TaschenrechnerV42_Einfach {
    static String POSSIBLE_OPERATIONS = "+-*/";

    static void main() {
        Scanner scanner = new Scanner(System.in);
        MyStack stack = new MyStack();

        System.out.println(stack);

        while (true) {
            System.out.print("↵ ");
            String input = scanner.next();

            if (input.matches("^\\d+$")) {
                stack.push(Double.parseDouble(input));
            } else if (POSSIBLE_OPERATIONS.contains(input)) {
                double num1 = stack.pop();
                double num2 = stack.pop();
                double result = 0;

                if (input.equals("+"))
                    result = num2 + num1;
                if (input.equals("-"))
                    result = num2 - num1;
                if (input.equals("*"))
                    result = num2 * num1;
                if (input.equals("/"))
                    result = num2 / num1;

                stack.push(result);
                System.out.println("Stack: " + stack);
            }
        }
    }
}























