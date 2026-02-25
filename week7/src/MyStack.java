import java.util.EmptyStackException;

public class MyStack {
    static MyList li = new MyList();


    public void push(double x) {
        li.add(x);
    }

    public double pop() {
        checkIfOutOfBounds();

        return li.remove(li.size() - 1);
    }

    public double peek() {
        checkIfOutOfBounds();

        return li.get(li.size() - 1);
    }

    public double size() {
        return li.size();
    }

    public void clear() {
        li.clear();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < li.size(); i++) {
            sb.append(li.get(i));
            if (i < li.size() - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    } // String-Repräsentation des Stacks: z.B. [2.34, 14.0, 3.14, 4.0]

    private void checkIfOutOfBounds() {
        if (li.size() == 0) throw new EmptyStackException();
    }
}
