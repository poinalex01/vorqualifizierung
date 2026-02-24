import java.util.List;

public class MyStack {
    static void main() {
        MyStack stack = new MyStack();

        stack.push(2.34);
        stack.push(14.0);
        stack.push(3.14);
        stack.push(4.0);

        System.out.println(stack); // [2.34, 14.0, 3,14, 4,0]
        System.out.println(stack.pop()); // 4,0
        System.out.println(stack.peek()); // 3,14
        System.out.println(stack); // [2.34, 14.0, 3.14]
    }


    private double[] data = new double[1];

    /**
     * Tracks the number of stored elements (next available index).
     */
    private int size = 0;


    public void push(double x) {
        add(x);
    }

    public double pop() {
        checkIfOutOfBounds();

        return remove(size - 1);
    }

    public double peek() {
        checkIfOutOfBounds();

        return get(size - 1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    } // String-Repräsentation des Stacks: z.B. [2.34, 14.0, 3.14, 4.0]

    private void checkIfOutOfBounds() {
        if (size == 0)
            throw new IndexOutOfBoundsException("No elements available!");
    }

    /**
     * Adds a value to the end of the list.
     *
     * @param v the value to add
     */
    public void add(double v) {
        if (size == data.length) resize();

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
    public void add(int pos, double v) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException("Invalid index: " + pos);

        if (size == data.length) resize();

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
    public double get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("Ungültiger Index: " + idx);
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
    public double remove(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException("Ungültiger Index: " + idx);

        double removed = data[idx];
        for (int i = idx; i < size - 1; i++)
            data[i] = data[i + 1];

        data[size - 1] = 0;

        size--;
        return removed;
    }

    /**
     * Returns the current number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the list.
     * The internal array remains but is considered empty.
     */
    public void clear() {
        size = 0;
        // data = new double[size];
    }

    /**
     * Returns a string representation of the list's contents.
     *
     * @return a string with all elements separated by spaces
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
            sb.append(data[i]).append(" ");
        return sb.toString();
    }

    /*
     * Doubles the size of the internal array when it's full.
     */
    private void resize() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
