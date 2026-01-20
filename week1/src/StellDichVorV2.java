import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("First Name:");
    String firstName = scanner.nextLine();

    System.out.println("Last Name:");
    String lastName = scanner.nextLine();

    System.out.println("Age:");
    int age = scanner.nextInt();

    System.out.println("Income:");
    double income = scanner.nextDouble();

    System.out.println("Trusting:");
    boolean isTrusting = scanner.nextBoolean();

    System.out.println("Mein Name ist " + firstName + " " + lastName + ".\n" +
            "Ich bin " + age + " Jahre alt und wohne derzeit in Linz.\n" +
            "Der letzte Buchstabe meines Nachnamens ist " + lastName.charAt(lastName.length() - 1) + ".\n" +
            "Nach der Ausbildung werde ich " + income + " EURO/h verdienen, und glaube heute an mich: " + isTrusting + "."
    );
}