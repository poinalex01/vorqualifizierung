void main() {
    MyList.add(1.1);
    MyList.add(2.2);
    MyList.add(3.3);

    System.out.println("Liste:");
    System.out.println(MyList.print());
    System.out.println("MyList.size: " + MyList.size());

    MyList.add(1, 9.9);
    System.out.println();
    System.out.println("added at pos 1: 9.9):");
    System.out.println(MyList.print());
    System.out.println("MyList.size: " + MyList.size());

    System.out.println();
    System.out.println("get(2)= " + MyList.get(2));

    double removed = MyList.remove(1);
    System.out.println();
    System.out.println("remove(1)= removed: " + removed);
    System.out.println(MyList.print());
    System.out.println("MyList.size= " + MyList.size());

    MyList.clear();
    System.out.println();
    System.out.println("cleared list:");
    System.out.println(MyList.print());
    System.out.println("MyList.size= " + MyList.size());
}


public class MyList {
    /** Internal array to store the elements. */
    private static double[] data = new double[1];

    /** Tracks the number of stored elements (next available index). */
    private static int size = 0;


    /**
     * Adds a value to the end of the list.
     *
     * @param v the value to add
     */
    public static void add(double v) {
        if (size == data.length)
            resize();

        data[size] = v;
        size++;
    }

    /**
     * Inserts a value at a specific position in the list.
     * Elements after the position are shifted one place to the right.
     *
     * @param pos the index where the value should be inserted (0-based)
     * @param v   the value to insert
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    public static void add(int pos, double v) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException("Invalid index: " + pos);

        if (size == data.length)
            resize();

        for (int i = size; i > pos; i--) // move right
            data[i] = data[i - 1];

        data[pos] = v;
        size++;
    }

    /**
     * Returns the value at the specified index.
     *
     * @param idx the index of the element to retrieve
     * @return the value at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public static double get(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException("Ungültiger Index: " + idx);
        return data[idx];
    }

    /**
     * Removes the element at the specified index.
     * Elements after the index are shifted one place to the left.
     *
     * @param idx the index of the element to remove
     * @return the removed value
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public static double remove(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException("Ungültiger Index: " + idx);

        double removed = data[idx];
        for (int i = idx; i < size - 1; i++)
            data[i] = data[i + 1];

        size--;
        return removed;
    }

    /**
     * Returns the current number of elements in the list.
     *
     * @return the size of the list
     */
    public static int size() {
        return size;
    }

    /**
     * Removes all elements from the list.
     * The internal array remains but is considered empty.
     */
    public static void clear() {
        size = 0;
        // data = new double[size];
    }

    /**
     * Returns a string representation of the list's contents.
     *
     * @return a string with all elements separated by spaces
     */
    public static String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
            sb.append(data[i]).append(" ");
        return sb.toString();
    }

    /*
     * Doubles the size of the internal array when it's full.
     */
    private static void resize() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}