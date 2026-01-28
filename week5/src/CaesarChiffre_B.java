void main() {
    Scanner scanner = new Scanner(System.in);
    // String str = scanner.nextLine();
    final String pass = "IBIS";
    String original = "HELLOXWORLDX ";

    String normalized = normalize(original);
    String encrypted = crypt(normalized, pass, true);
    String decrypted = crypt(encrypted, pass, false);

    System.out.println("str: " + original);
    System.out.println("normalized: " + normalized);
    System.out.println("encrypted: " + encrypted); // PFTDWYEGZMLP
    System.out.println("decrypted: " + decrypted);
}

static String crypt(String text, String pass, boolean encrypt) {
    char[] chars = text.toCharArray();

    int keyIndex = 0;
    for (int i = 0; i < chars.length; i++) {
        int key = pass.charAt(keyIndex) - 'A';
        int shift;

        if (encrypt) shift = key;
        else shift = -key;

        char a = (char) (chars[i] + shift);
        if (a > 'Z') a -= 26;
        if (a < 'A') a += 26;

        chars[i] = a;

        keyIndex++;
        if (keyIndex == pass.length()) keyIndex =0;
    }

    StringBuilder sb = new StringBuilder();
    for (char currentChar : chars) {
        sb.append(currentChar);
    }
    return sb.toString();
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
    StringBuilder sb = new StringBuilder();

    char[] chars = text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char a = (char) (chars[i] + key);

        if (a > 'Z')
            a -= 26;

        sb.append(text, 0, i).append(a).append(text.substring(i + 1));
    }
    return sb.toString();
}

static String decrypt(String text, int key) {
    return encrypt(text, key-3);
}