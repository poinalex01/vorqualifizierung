void main() {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    String[] namen = {"Frida", "Klaus", "Alfons", "Lisa", "Bert", "Walter"};
    String[] verben = {"singt", "denkt", "kichert", "rechnet", "weint", "lacht", "kocht"};

    System.out.println("Wieviele Sätze?: ");
    int sentenceCounter = scanner.nextInt();
    // Verwende die Methode nextInt(...) der Klasse Random um einen zufälligen Index
    // für die Auswahl eines Wortes aus den Arrays zu bestimmen.
    //
    //Gestalte dein Programm so, dass es auch funktioniert, wenn sich die Länge der Arrays ändert.

    for (int i = 0; i < sentenceCounter; i++) {
        System.out.println(namen[random.nextInt(namen.length)] + " " + verben[random.nextInt(verben.length)] + ".");
    }
}