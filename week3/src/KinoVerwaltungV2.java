final int GAMBLE_PRICE = 5;
final int TICKET_PRICE = 15;
final int GAMBLE_AMOUNT_WIN = 25;

void main() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> chosenMovies = new ArrayList<>();
    final String[][] movies = {
            {"Batman", "20:15", "1", "1", "14.00"},
            {"Matrix", "22:00", "3", "2", "12.50"},
            {"Matrix 2", "17:00", "2", "0", "9.99"}
    };
    double money;

    do {
        System.out.println("Wie viel Geld hast du?");
        money = scanner.nextDouble();

        if (money < TICKET_PRICE) {
            System.out.println("Gib einen gültigen Betrag ein!\nDu brauchst mindestens 15€.");
        }
    } while (money < TICKET_PRICE);

    int wantToDo;
    mainLoop:
    do {
        System.out.println("\nWas willst du als nächstes tun? (Du hast noch " + money + "€)\n" +
                "1. Ticket kaufen (von KinoVerwaltungV1)\n" +
                "2. Film ansehen\n" +
                "3. Gewinnspiel\n" +
                "4. Kino verlassen"
        );

        switch (wantToDo = scanner.nextInt()) {
            case 1: {
                money = buyTickets(movies, chosenMovies, money);
                break;
            }
            case 2: {
                watchMovies(movies, chosenMovies);
                break;
            }
            case 3: {
                money = gamble(money);
                break;
            }
            case 4: {
                System.out.println("Du verlässt das Kino. Auf wiedersehen!");
                break mainLoop;
            }
        }
    } while (wantToDo != 0);
}


void displayFilms(String[][] films) {
    System.out.println("Filmnr   Filmname         Uhrzeit  Saal     Preis     Restplätze");
    System.out.println("-".repeat(64));
    for (int i = 0; i < films.length; i++) {
        String availability = Integer.parseInt(films[i][3]) > 0 ? "verfuegbar" : "ausgebucht";
        System.out.printf("%-8d %-16s %-8s %-8s %-8s %-8s\n", i + 1, films[i][0], films[i][1], films[i][2], films[i][4], availability);
    }

    for (int i = 0; i < films.length; i++) {
        
    }
    /*
    {"Batman", "20:15", "1", "1", "14.00"},
    {"Matrix", "22:00", "3", "2", "12.50"},
    {"Matrix 2", "17:00", "2", "0", "0.99"}
    */
    System.out.println("-".repeat(64));
}

private double buyTickets(String[][] movies, ArrayList<Integer> chosenMovies, double money) {
    Scanner scanner = new Scanner(System.in);
    int movieChoice;

    do {
        displayFilms(movies);
        System.out.println("chosenMovies: ");
        System.out.println(chosenMovies);

        do {
            System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen? (0 zum abbrechen)");
            movieChoice = scanner.nextInt();

            if (movieChoice < 0 || movieChoice > movies.length) {
                System.out.println("Gib eine gültige Film Nummer ein!");
            }
        } while (movieChoice < 0 || movieChoice > movies.length);

        if (movieChoice != 0) {
            int amountAvailable = Integer.parseInt(movies[movieChoice - 1][3]);
            if (amountAvailable > 0) {
                int amountWanted;
                double currentTicketPrice = Double.parseDouble(movies[movieChoice - 1][4]);

                do {
                    System.out.printf("Es sind noch %s Tickets um jeweils %.2f€ dafuer verfuegbar. Wie viele möchtest du kaufen?", amountAvailable, currentTicketPrice);
                    amountWanted = scanner.nextInt();
                    if (amountWanted <= 0 || amountWanted > amountAvailable) {
                        System.out.println("Gib eine gültige Anzahl ein!");
                    }
                } while (amountWanted <= 0 || amountWanted > amountAvailable);

                double currentPrice = amountWanted * currentTicketPrice;
                if (money >= currentPrice) {
                    money -= currentPrice;

                    System.out.printf("Du kaufst %s Tickets um %.2f€ und hast jetzt noch %.2f€\n", amountWanted, currentPrice, money);
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
    return money;
}

private void watchMovies(String[][] movies, ArrayList<Integer> chosenMovies) {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Integer> map = new HashMap<>();

    if (!chosenMovies.isEmpty()) {
        System.out.println("Welchen Film (für den du noch ein Ticket hast) möchtest du sehen? (0 für abbrechen)");

        for (Integer movieID : chosenMovies) {
            if (!map.containsKey(movieID)) {
                map.put(movieID, 1);
            } else {
                map.put(movieID, map.getOrDefault(movieID, 0) + 1);
            }
        }

        int index = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(index + ". " + movies[entry.getKey() - 1][0] + " (x " + entry.getValue() + " Tickets)");
            index++;
        }

        int wantedMovie;
        do {
            wantedMovie = scanner.nextInt();

            if (wantedMovie < 0 || wantedMovie > chosenMovies.size()) {
                System.out.println("Ungültige Eingabe!\nVersuche erneut:");
            }
        } while (wantedMovie < 0 || wantedMovie > chosenMovies.size());

        chosenMovies.remove(wantedMovie - 1);
        System.out.printf("Du schaust dir den Film %s an. Viel Spaß!", movies[wantedMovie - 1][0]);
    } else {
        System.out.println("Du hast noch keine Tickets!");
    }
}

private double gamble(double money) {
    Random random = new Random();
    System.out.println("Du machst beim Gewinnspiel mit!");

    money -= GAMBLE_PRICE;
    int luckyNumber = random.nextInt(10, 1001);
    System.out.println("Deine Glückszahl ist: " + luckyNumber);

    int sum = 0, temp = luckyNumber;
    while (temp > 0) {
        sum += temp % 10;
        temp /= 10;
    }

    if (sum >= 16) {
        money += GAMBLE_AMOUNT_WIN;
        System.out.println("Die Ziffernsumme deiner Glückszahl ist größer 16. Du gewinnst 20€!");
    } else {
        System.out.println("Die Ziffernsumme deiner Glückszahl ist kleiner 16. Leider nicht gewonnen.");
    }

    return money;
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

// TODO: liste vom ausgeben passt nicht - soll die echte ID anzeigen, nicht die vom zusammengezaehlten array!