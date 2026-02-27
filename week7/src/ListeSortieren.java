import java.util.Arrays;
import java.util.List;

public class ListeSortieren {
    /*
        Wähle einen der bereits mit Arrays implementierten Sortieralgorithmus und ändere die Implementation derart, dass statt Arrays Listen verwendet werden.
        * Wichtig: Es soll NICHT die eingebaute Sortierfunktion des Listeninterface, sondern euer eigener Algorithmus verwendet werden.
    */
    static void main() {
        List<Integer> list = Arrays.asList(2, 63, 21, 11, 15, 18, 4, 1);
        System.out.println("list = " + list);
        bubbleSort(list);
        System.out.println("list = " + list);
    }

    static void bubbleSort(List<Integer> list) {
        int left, right;

        for (left = 0; left < list.size() - 1; left++) {
            for (right = 0; right < list.size() - left - 1; right++) {
                if (list.get(right) > list.get(right + 1)) {
                    int temp = list.get(right);
                    list.set(right, list.get(right + 1));
                    list.set(right + 1, temp);
                }
            }
        }
    }
}
