void main() {
    Scanner scanner = new Scanner(System.in);
    // String str = scanner.nextLine();
    final int key = 3;
    String str = "HALLO XY";

    String normalized = normalize(str);
    String encrypted = encrypt(normalized, key);
    String decrypted = decrypt(encrypted, key);

    System.out.println("str: " + str);
    System.out.println("normalized: " + normalized);
    System.out.println("encrypted: " + encrypted); // KDOORAB
    System.out.println("decrypted: " + decrypted);
}


static String normalize(String text) {
    text = text.toUpperCase();

    text = text.replaceAll("Ä", "AE");
    text = text.replaceAll("Ö", "OE");
    text = text.replaceAll("Ü", "UE");

    // nicht zulässige Zeichen ignorieren,
    return text.replaceAll("[^A-Z]", "");
}

static String encrypt(String text, int key) {
    char[] chars = text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char a = (char) (chars[i] + key);

        if (a > 'Z')
            a -= 26;

        text = text.substring(0, i) + a + text.substring(i + 1);
    }
    return text;
}

static String decrypt(String text, int key) {
    key *= -1; // negative key

    char[] chars = text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char a = (char) (chars[i] + key);

        if (a < 'A')
            a += 26;

        text = text.substring(0, i) + a + text.substring(i + 1);
    }
    return text;
}