void main() {
    String input = "Jeder wackere Bayer vertilgt bequem zwo Pfund Kalbshaxen.";
    // String input = "The quick brown fox jumps over the lazy dog.";
    System.out.printf("%s\nisPangram: %s", input, isPangram(input));
}


/**
 * Checks if the given string is a pangram.
 * A pangram contains every letter of the alphabet at least once.
 *
 * @param s The string to check.
 * @return true if the string is a pangram, false otherwise.
 */
public static boolean isPangram(String s) {
    s = s.toUpperCase();
    Set<Character> alphabet = new HashSet<>();
    for (char c = 'A'; c <= 'Z'; c++)
        alphabet.add(c);

    for (char c : s.toCharArray())
        alphabet.remove(c);

    return alphabet.isEmpty();
}