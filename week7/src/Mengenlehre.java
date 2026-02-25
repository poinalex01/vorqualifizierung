import java.util.HashSet;

public class Mengenlehre {
    static void main() {
        HashSet<Integer> A = new HashSet<>();
        A.add(1);
        A.add(3);
        A.add(5);

        HashSet<Integer> B = new HashSet<>();
        B.add(3);
        B.add(5);
        B.add(7);

        HashSet<Integer> C = new HashSet<>();
        C.add(5);
        C.add(7);
        C.add(9);

        System.out.println("A ∪ B = " + getUnionList(A, B));
        System.out.println("A ∪ C = " + getUnionList(A, C));
        System.out.println("B ∪ C = " + getUnionList(B, C));

        System.out.println("A ∩ B = " + getIntersectionList(A, B));
        System.out.println("A ∩ C = " + getIntersectionList(A, C));
        System.out.println("B ∩ C = " + getIntersectionList(B, C));

        System.out.println("A \\ B = " + getDifferenceList(A, B));
        System.out.println("A \\ C = " + getDifferenceList(A, C));
        System.out.println("B \\ C = " + getDifferenceList(B, C));
        System.out.println("C \\ B = " + getDifferenceList(C, B));
        System.out.println("C \\ A = " + getDifferenceList(C, A));
        System.out.println("B \\ A = " + getDifferenceList(B, A));

        HashSet<Integer> unionABC = getUnionList(getUnionList(A, B), C);
        System.out.println("A ∪ B ∪ C = " + unionABC);

        // 6. Beispiel: A ∪ (B ∩ C)
        HashSet<Integer> example = getUnionList(A, getIntersectionList(B, C));
        System.out.println("A ∪ (B ∩ C) = " + example);
    }

    public static HashSet<Integer> getUnionList(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static HashSet<Integer> getIntersectionList(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static HashSet<Integer> getDifferenceList(HashSet<Integer> set1, HashSet<Integer> set2) {
        HashSet<Integer> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}