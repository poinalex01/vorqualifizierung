import java.util.Arrays;

public class ListeSortieren {
    /*
        Wähle einen der bereits mit Arrays implementierten Sortieralgorithmus und ändere die Implementation derart, dass statt Arrays Listen verwendet werden.
        * Wichtig: Es soll NICHT die eingebaute Sortierfunktion des Listeninterface, sondern euer eigener Algorithmus verwendet werden.
    */
    static void main() {
        int[] arr = {2,63,21,11,15,18,4,1};
        System.out.println("arr = " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    static void bubbleSort(int[] arr){
        int left, right;

        for (left = 0; left < arr.length - 1; left++) {
            for (right = 0; right < arr.length - left - 1; right++) {
                if (arr[right] > arr[right + 1]) {
                    // int temp = arr[right];
                    // arr[right] = arr[right + 1];
                    // arr[right + 1] = temp;

                    arr[right] = arr[right] ^ arr[right + 1];
                    arr[right + 1] = arr[right] ^ arr[right + 1];
                    arr[right] = arr[right] ^ arr[right + 1];
                }
            }
        }
    }
}
