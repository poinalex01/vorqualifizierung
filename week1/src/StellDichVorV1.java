void main() {
    String firstName = "Alexander";
    String lastName = "Poinstingl";
    int age = 25;
    char lastLetter = lastName.charAt(lastName.length() - 1);
    double income = 50.0;
    boolean isTrusting = true;

    System.out.println("Mein Name ist " + firstName + " " + lastName + ".\n" +
            "Ich bin " + age + " Jahre alt und wohne derzeit in Linz.\n" +
            "Der letzte Buchstabe meines Nachnamens ist " + lastLetter + ".\n" +
            "Nach der Ausbildung werde ich " + income + " EURO/h verdienen, und glaube heute an mich: " + isTrusting + "."
    );
}