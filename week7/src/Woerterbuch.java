import java.util.HashMap;
import java.util.Map;

public class Woerterbuch {
    static Map<String, String> engToGerMap = new HashMap<>(Map.of(
            "hello", "hallo",
            "car", "auto",
            "house", "haus",
            "tree", "baum",
            "water", "wasser",
            "book", "buch",
            "dog", "hund",
            "cat", "katze"
    ));

    static Map<String, String> gerToEngMap = new HashMap<>();

    static void main() {
        for (var entry : engToGerMap.entrySet())
            gerToEngMap.put(entry.getValue(), entry.getKey());

        System.out.println("car == " + engToGer("car"));
        System.out.println("auto == " + gerToEng("auto"));


        System.out.println("\nmap before deleting: " + engToGerMap);
        System.out.println("deleteEngWord(\"tree\") = " + deleteEngWord("tree"));
        System.out.println("deleteGerWord(\"auto\") = " + deleteGerWord("auto"));
        System.out.println("deleteGerWord(\"unavailableWord\") = " + deleteGerWord("unavailableWord"));
        System.out.println("map after deleting: " + engToGerMap);


        System.out.println("\nmap before adding: " + engToGerMap);
        addNewWord("ant", "ameise");
        System.out.println("map after adding: " + engToGerMap);
    }

    static String engToGer(String engWord) {
        return engToGerMap.getOrDefault(engWord, "word not found");
    }

    static String gerToEng(String gerWord) {
        return gerToEngMap.getOrDefault(gerWord, "word not found");
    }

    static void addNewWord(String engWord, String gerWord) {
        if (engToGerMap.containsKey(engWord) || gerToEngMap.containsKey(gerWord)) {
            System.out.println("maps already contains (" + engWord + " " + gerWord + ")");
        } else {
            gerToEngMap.putIfAbsent(gerWord, engWord);
            engToGerMap.putIfAbsent(engWord, gerWord);
        }
    }

    static String deleteEngWord(String engWord) {
        String ger = engToGerMap.remove(engWord);

        if (ger == null)
            return "'" + engWord + "' doesn't exist";

        gerToEngMap.remove(ger);
        return ger;
    }

    static String deleteGerWord(String gerWord) {
        String eng = gerToEngMap.remove(gerWord);

        if (eng == null)
            return "'" + gerWord + "' doesn't exist";

        engToGerMap.remove(eng);
        return eng;
    }
}