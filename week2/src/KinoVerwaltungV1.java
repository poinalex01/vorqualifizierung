// https://ds2.at/lms/mod/assign/view.php?id=69
void main() {
    Scanner scanner = new Scanner(System.in);

    String[][] movies = {
            {"Batman", "20:15", "1", "1"},
            {"Matrix", "22:00", "3", "2"},
            {"Matrix 2", "17:00", "2", "0"}
    };
    ArrayList<Integer> chosenMovies = new ArrayList<>();

    double money;
    int movieChoice;
    final int TICKET_PRICE = 15;

    do {
        System.out.println("Wie viel Geld hast du?");
        money = scanner.nextDouble();

        if (money < TICKET_PRICE) {
            System.out.println("Gib einen gültigen Betrag ein!\nDu brauchst mindestens 15€.");
        }
    } while (money < TICKET_PRICE);

    do {
        displayFilms(movies);

        do {
            System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen? (0 zum abbrechen)");
            movieChoice = scanner.nextInt();

            if (movieChoice < 0 || movieChoice > movies.length + 1) {
                System.out.println("Gib eine gültige Film Nummer ein!");
            }
        } while (movieChoice < 0 || movieChoice > movies.length);

        if (movieChoice != 0) {
            int amountAvailable = Integer.parseInt(movies[movieChoice - 1][3]);
            if (amountAvailable > 0) {
                int amountWanted;

                do {
                    System.out.println("Es sind noch " + amountAvailable + " Tickets um jeweils 15€ dafür verfügbar. Wie viele möchtest du kaufen?");
                    amountWanted = scanner.nextInt();
                    if (amountWanted <= 0 || amountWanted > amountAvailable) {
                        System.out.println("Gib eine gültige Anzahl ein!");
                    }
                } while (amountWanted <= 0 || amountWanted > amountAvailable);

                int currentPrice = amountWanted * TICKET_PRICE;
                if (money >= currentPrice) {
                    money -= currentPrice;

                    System.out.printf("Du kaufst %s Tickets um %d€ und hast jetzt noch %.2f€\n", amountWanted, currentPrice, money);
                    for (int i = 0; i < amountWanted; i++) {
                        chosenMovies.add(movieChoice);
                    }

                    // Plaetze im movies Array anpassen
                    movies[movieChoice - 1][3] = String.valueOf(Integer.parseInt(movies[movieChoice - 1][3]) - amountWanted);
                } else {
                    System.out.println("Du hast leider nicht genug Geld!");
                }
            } else {
                System.out.println("Dieser Film ist leider schon ausgebucht.");
            }
        }
    } while (movieChoice != 0);

    System.out.println("Deine gekauften Filme sind: " + chosenMovies);
    System.out.println("Tschuess!");
}

void displayFilms(String[][] films) {
    System.out.println("Filmnr   Filmname         Uhrzeit  Saal     Restplätze");
    System.out.println("-".repeat(64));
    for (int i = 0; i < films.length; i++) {
        String availblitly = Integer.parseInt(films[i][3]) > 0 ? "verfuegbar" : "ausgebucht";
        System.out.printf("%-8d %-16s %-8s %-8s %-8s\n", i + 1, films[i][0], films[i][1], films[i][2], availblitly);
    }
    System.out.println("-".repeat(64));
}



/*
    bei jedem input wieder ueberpruefen, ob die Eingabe gültig ist

    Film ansehen:
    kann man auch mehrere Filme auswaehlen -> in Array speichern
    mehrfach gekaufte tickets anzeigen mit '1x/Filmart.'

    Gewinnspiel:
    5€ Gewinnspiel -> Constante
    die 5€ abrechnen bevor man den preis ausgibt, sonst passt womöglich die Endsumme nicht
        ((da das Gewinnspiel-Los 5€ gekostet hat, hat er also jetzt 20€ mehr als vorher))
    Glückszahl von String in einzelne ints umwandeln und dann zusammenrechnen ("397" ist 3+9+7)
*/