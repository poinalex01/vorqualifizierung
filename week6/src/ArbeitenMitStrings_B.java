void main() {
    /*
    Text verkehrt
    Schreibe ein Programm, das einen String umkehrt.
        Beispiel:
        "Hallo" ==> "ollaH"
    */
    String input = "Hallo";
    System.out.printf("Eingabe: %s\nAusgabe: %s", input, reverse(input));


    System.out.println();


    /*
    Wörter im Satz umdrehen
    Drehe nicht den ganzen Satz um, sondern jedes Wort einzeln.
        Eingabe: "Java macht Spaß"
        Ausgabe: "avaJ thcam ßapS"
    */
    input = "Java macht Spaß";
    StringBuilder output = new StringBuilder();
    for (String word : input.split(" ")) {
        output.append(reverse(word)).append(" ");
    }
    System.out.printf("Eingabe: %s\nAusgabe: %s", input, output);


    System.out.println();



    /*
    Palindrom prüfen
    Schreibe eine Methode, das prüft, ob ein String ein Palindrom ist (vorwärts und rückwärts gleich).
        "anna" → Palindrom
        "lagerregal" → Palindrom
        "java" → kein Palindrom
    Hinweis: Wandle deinen String zuerst in Groß- oder Kleinbuchstaben um (.toUpperCase(), .toLowerCase())
        "anna" → Palindrom
        "lagerregal" → Palindrom
        "java" → kein Palindrom
    */
    input = "java";
    System.out.printf("Eingabe: %s\nisPalindrome: %s", input, isPalindrome(input));
}


/**
 * Reverses the given string.
 *
 * @param s The string to reverse.
 * @return The reversed string.
 */
public static String reverse(String s) {
    StringBuilder reversed = new StringBuilder();
    for (int i = 0; i < s.length(); i++)
        reversed.insert(0, s.charAt(i));

    return reversed.toString();
}

/**
 * Checks if the given string is a palindrome.
 *
 * @param s The string to check.
 * @return true if the string is a palindrome, false otherwise.
 */
public static boolean isPalindrome(String s) {
    s = s.toUpperCase();
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != s.charAt(s.length() - i-1))
            return false;
    }

    return true;
}