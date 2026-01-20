import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Wie alt bist du?");
    int age = scanner.nextInt();
    System.out.println("Hast du deinen Führerschein dabei?");
    boolean license = scanner.nextBoolean();
    System.out.println("Wie viel Bier hast du getrunken?");
    int beer = scanner.nextInt();
    System.out.println("Wie viel Shots hast du getrunken?");
    int shots = scanner.nextInt();

    int drinks = beer + shots;

    if (age >= 17 && license && ((age < 19 && drinks == 0) || (age >= 19 && drinks <= 2))) {
        System.out.println("Du darfst Auto fahren");
    } else {
        System.out.println("Du darfst nicht Autofahren");
    }
}