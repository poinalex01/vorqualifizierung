/*
Großbuchstaben: 3
Kleinbuchstaben: 51
Zahlen: 2
Sonstige Zeichen: 16
* */

void main() {
    String sentence = "Unter #Handy finden sich 6 große Anbieter, 2 davon sind aber die besten!";
    int upperCaseLetters = 0, lowerCaseLetters = 0, numbers = 0, otherCharacters = 0;

    for (char c : sentence.toCharArray()) {
        // 65 - 90  uppercase
        // 97 - 122 lowercase
        // 48 - 57  numbers

        if (c >= 65 && c <= 90) {
            upperCaseLetters++;
        } else if (c >= 97 && c <= 122) {
            lowerCaseLetters++;
        } else if (c >= 48 && c <= 57) {
            numbers++;
        } else {
            otherCharacters++;
        }
    }

    System.out.println("upperCaseLetters: " + upperCaseLetters + "\n"
            + "lowerCaseLetters: " + lowerCaseLetters + "\n"
            + "numbers: " + numbers + "\n"
            + "otherCharacters: " + otherCharacters
    );
}