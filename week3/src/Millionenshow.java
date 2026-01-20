
void main() {
    Scanner scanner = new Scanner(System.in);
    String[][] QUESTIONS = {
            {"Was bedeutet HTML?", "HyperText Markup Language", "HighText Machine Language", "Hyperlink and Text Markup Language", "1"},
            {"Welches Ergebnis liefert folgender Ausdruck: (true&&false||true)", "false", "true", "2"},
            {"Die Netzwerk-Technik verwendet man ...", "beim Fischen", "zum Vernetzen von Computern", "2"},
            {"Ein Null-pointer ...", "gibt es nicht", "ist eine Referenz, die auf kein Objekt zeigt", "Datentyp für Text", "ärgerlich", "2"},
            {"Eine 'if-Schleife' ist ...", "eine Schleif-Machine für Tischler", "ein Kündigungsgrund", "gibt es nicht", "3"},
            {"Was macht ein Compiler?", "Er analysiert Netzwerke", "Er führt Programme aus", "Er wandelt Quellcode in Maschinencode um", "Er speichert Dateien", "3"}
    };
    ArrayList<Integer> remainingQuestions = IntStream.range(0, QUESTIONS.length).boxed().collect(Collectors.toCollection(ArrayList::new));
    Collections.shuffle(remainingQuestions);

    int money = 100;


    for (Integer currentQuestionIndex : remainingQuestions) {
        String[] currentQuestion = QUESTIONS[currentQuestionIndex];

        System.out.println(currentQuestion[0]);
        System.out.println("-".repeat(32));
        for (int j = 1; j < currentQuestion.length - 1; j++) {
            System.out.println(j + ". " + currentQuestion[j]);
        }

        int answer;
        do {
            System.out.println("Deine Antwort: ");
            answer = scanner.nextInt();
            if (answer < 1 || answer > currentQuestion.length - 1) {
                System.out.println("Ungueltige Eingabe!");
            }
        } while (answer < 1 || answer > currentQuestion.length - 1);

        if (answer == Integer.parseInt(currentQuestion[currentQuestion.length - 1])) {
            System.out.println("Richtig");
            money *= 2;
        } else {
            System.out.println("Falsch");
            money /= 10;
        }

        System.out.println("\n\n");
    }

    System.out.println("Gewinn: " + money);
}