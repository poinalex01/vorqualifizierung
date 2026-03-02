import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Bibliothek {
    static HashMap<String, List<String>> authorBookMap = new HashMap<>(Map.of("Franz Kafka", List.of("Die Verwandlung", "Der Prozess", "Das Schloss"), "Johann Wolfgang von Goethe", List.of("Faust", "Die Leiden des jungen Werther", "Egmont"), "J.K. Rowling", List.of("Harry Potter und der Stein der Weisen", "Harry Potter und die Kammer des Schreckens", "Harry Potter und der Gefangene von Askaban"), "George Orwell", List.of("1984", "Farm der Tiere"), "Hermann Hesse", List.of("Siddhartha", "Der Steppenwolf", "Demian"), "F. Scott Fitzgerald", List.of("Der große Gatsby", "Zärtlich ist die Nacht"), "Markus Zusak", List.of("Die Bücherdiebin"), "Jane Austen", List.of("Stolz und Vorurteil", "Emma"), "Leo Tolstoi", List.of("Krieg und Frieden", "Anna Karenina"), "Harper Lee", List.of("Wer die Nachtigall stört")));
    static Map<String, String> bookAuthorMap = new HashMap<>();


    static void main() {
        // fill up bookAuthorMap for more performance later on
        for (Map.Entry<String, List<String>> entry : authorBookMap.entrySet()) {
            for (String book : entry.getValue())
                bookAuthorMap.put(book, entry.getKey());
        }

        printAllAuthors();
        printBooksOfAuthor("J.K. Rowling");
        getAuthorByBook("Die Verwandlung");

        System.out.println("ADDING BOOKS");
        addBook("Alex", "BookOfLife");
        addBooks("Alex", List.of("book1", "book2", "book3"));
        printAll();
    }

    static void addBook(String authorName, String bookName) {
        List<String> bookList = authorBookMap.computeIfAbsent(authorName, k -> new ArrayList<>());

        if (!bookList.contains(bookName)) {
            bookList.add(bookName);
            bookAuthorMap.put(bookName, authorName);
            System.out.printf("Successfully added: '%s' by '%s'!\n", bookName, authorName);
        } else {
            System.out.printf("Book '%s' by '%s' already exists!\n", bookName, authorName);
        }
    }

    static void addBooks(String authorName, List<String> booksToAdd) {
        List<String> bookList = authorBookMap.computeIfAbsent(authorName, k -> new ArrayList<>());

        for (String book : booksToAdd) {
            if (!bookList.contains(book)) {
                bookList.add(book);
                bookAuthorMap.put(book, authorName);
                System.out.printf("Successfully added: '%s' by '%s'!\n", book, authorName);
            }
        }
    }

    // Liste aller Autoren ausgeben
    static void printAllAuthors() {
        System.out.println("Author List:");
        authorBookMap.keySet().forEach(System.out::println);
        System.out.println("-".repeat(64));
    }

    // Liste aller Bücher eines bestimmten Autors ausgeben
    static void printBooksOfAuthor(String authorName) {
        List<String> bookList = authorBookMap.get(authorName);
        System.out.printf("Books of %s:\n", authorName);
        if (bookList != null) {
            bookList.forEach(System.out::println);
        } else System.out.println("No books found for this author.");
        System.out.println("-".repeat(64));
    }
    /*
    static void printBooksOfAuthor(String authorName) {
        System.out.printf("Books of %s:\n", authorName);
        authorBookMap.get(authorName).forEach(System.out::println);
        System.out.println("-".repeat(64));
    }
    */

    // Suche den Autor eines bestimmten Titels
    static void getAuthorByBook(String bookName) {
        System.out.printf("The Author of '%s' is: '%s'\n", bookName, bookAuthorMap.get(bookName));
        System.out.println("-".repeat(64));
    }

    // Liste alle Titeln, welche in der Bibliothek verfügbar sind, inkl. Autor im Format: 'Die Verwandlung' von 'Franz Kafka'
    static void printAll() {
        System.out.println("List of all Titles:");
        bookAuthorMap.forEach((bookName, authorName) -> {
            System.out.printf("'%s' von '%s'\n", bookName, authorName);
        });
        System.out.println("-".repeat(64));
    }
}