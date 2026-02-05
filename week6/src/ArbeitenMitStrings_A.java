void main() {
    String s = "Banane";
    System.out.printf("Länge: %s\nErster Buchstabe: %s\nLetzter Buchstabe: %s",
            s.length(), s.charAt(0), s.charAt(s.length() - 1)
    );


    System.out.println();


    String text = "Java ist eine tolle Sprache";
    System.out.println(text.split(" ").length + " Wörter");


    System.out.println();


    /*
    Zeichen ersetzen:
        Eingabe: "Java ist toll"
        Ausgabe: "Java-ist-toll"
    */
    String input = "Java ist toll";
    System.out.printf("Eingabe: %s\nAusgabe: %s%n", input, input.replaceAll(" ", "-"));


    System.out.println();



    /*
    Vokale zählen
        Eingabe: "Hallo Welt"
        Ausgabe: 3 Vokale
    */
    input = "Hallo Welt";
    int vokalCount = 0;
    String vokale = "aeiou";

    for (char c : input.toCharArray()) {
        if (vokale.indexOf(c) != -1)
            vokalCount++;
    }
    System.out.printf("Eingabe: %s\nAusgabe: %d", input, vokalCount);


    System.out.println();
    System.out.println();


    /*
    Erstes und letztes Vorkommen
        Text: "Programmieren"
        Suche nach dem Zeichen: r

        Ausgabe:
        Erstes Vorkommen: 1
        Letztes Vorkommen: 10
    */
    input = "Programmieren";
    char wantedChar = 'r';
    int first = input.indexOf(wantedChar);
    int last = input.lastIndexOf(wantedChar);

    System.out.printf("Erstes Vorkommen: %d\nLetztes Vorkommen: %d", first, last);
}