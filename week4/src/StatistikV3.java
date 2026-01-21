/*
    Das Ergebnis sollte in etwa so aussehen:

    Urliste:
    [0][10][3][8][9][1][3][9][6][0]
    Aufsteigend sortiert:
    [0][0][1][3][3][6][8][9][9][10]
    Arithmetisches Mittel: 4.9
    Spannweite: 10
    Median: 4.5
    Modalwert: 0 mit 2
    Mittlere absolute Abweichung: 3.5
*/

void main() {
    Random random = new Random();
    int[] urliste = random.ints(10, 0, 10).toArray();
    // int[] urliste = {0, 10, 3, 8, 9, 1, 3, 9, 6, 0};

    System.out.println("Urliste: ");
    printArray(urliste);

    System.out.println("Aufsteigend sortierte: ");
    printArray(sort(urliste));

    System.out.println("Arithmetisches Mittel: " + mean(urliste));
    System.out.println("Spannweite: " + (max(urliste) - min(urliste)));
    System.out.println("Median: " + median(urliste));
    int[] modalData = modal(urliste);
    System.out.println("Modalwert: " + modalData[0] + " mit " + modalData[1]);
    System.out.printf("Mittlere absolute Abweichung: %.1f%n", mad(urliste));
}

public static int[] sort(int[] numbers) {
    boolean isSorted;

    do {
        isSorted = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                int temp = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = temp;
                isSorted = false;
            }
        }
    } while (!isSorted);
    return numbers;
}

/**
 * Returns the minimum value from the given array of integers.
 *
 * @param values an array of integers
 * @return the minimum value in the array
 */
public static int min(int[] values) {
    int min = values[0];

    for (int n : values) {
        if (n < min)
            min = n;
    }

    return min;
}

/**
 * Returns the maximum value from the given array of integers.
 *
 * @param values an array of integers
 * @return the maximum value in the array
 */
public static int max(int[] values) {
    int max = values[0];

    for (int n : values) {
        if (n > max)
            max = n;
    }

    return max;
}

/**
 * Returns the sum of all values in the given array of integers.
 *
 * @param values an array of integers
 * @return the sum of the values in the array
 */
public static int sum(int[] values) {
    int sum = 0;

    for (int n : values) {
        sum += n;
    }

    return sum;
}

/**
 * Returns the mean (average) of the values in the given array of integers.
 *
 * @param values an array of integers
 * @return the mean of the values in the array
 */
public static double mean(int[] values) {
    return (double) sum(values) / values.length;
}

/**
 * Returns the median of the values in the given array of integers.
 * The array must be sorted before calling this method.
 *
 * @param values a sorted array of integers
 * @return the median value
 */
public static double median(int[] values) {
    // falls die Array-Länge gerade ist, nimm den Durchschnitt der beiden mittleren!)
    if (values.length % 2 == 0) {
        return (double) (values[values.length / 2] + values[values.length / 2 - 1]) / 2;
    }

    return (double) sum(values) / values.length;
}

/**
 * Returns the modal value and the modal count in the given sorted array of integers.
 * The modal value is the value that appears most frequently in the array.
 *
 * @param values a sorted array of integers
 * @return the [modal value, modal count] as an int-array
 */
public static int[] modal(int[] values) {
    int modalValue = values[0];
    int count = 0;

    HashMap<Integer, Integer> map = new HashMap<>();
    for (int n : values) {
        map.merge(n, 1, Integer::sum);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if (entry.getValue() > count) {
            modalValue = entry.getKey();
            count = entry.getValue();
        }
    }

    return new int[]{modalValue, count};
}

/**
 * Returns the Mean Absolute Deviation (MAD) of the values in the given array of integers.
 * The MAD is the average of the absolute deviations from the mean.
 *
 * @param values an array of integers
 * @return the Mean Absolute Deviation of the values
 */
public static double mad(int[] values) {
    double meanValue = mean(values);
    double sum = 0;

    for (int n : values) {
        sum += Math.abs(n - meanValue);
    }

    return sum / values.length;
}

public static void printArray(int[] values) {
    StringBuilder sb = new StringBuilder();

    for (int n : values) {
        sb.append("[").append(n).append("]");
    }

    System.out.println(sb);
}
