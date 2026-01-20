/*
    Lege mit Hilfe von Schleifen einen zweiten eindimensionalen Array mit 100 int-Werten an,
    den du mit Zufallszahlen zwischen 0 und 100 befüllst.


    Lege mit Hilfe von Schleifen einen dritten eindimensionalen Array mit 10 boolean Werten an,
    wobei auf jedes true in diesem Array ein false folgt und umgekehrt (es kommt also abwechselnd true und false vor).

    Anforderung 1: Programmiere dein Programm so, dass du nur an einer Stelle etwas ändern musst,
    um den letzten Array auf 100 Elemente erweitern zu können, und immer noch sind die Werte in dem Array abwechselnd true oder false sind.
* */

void main() {
    Random random = new Random();

    int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println("arr1 = " + Arrays.toString(arr1) + "\n");

    int[] arr2 = new int[100];
    for (int i = 0; i < arr2.length; i++) {
        arr2[i] = random.nextInt(101);
    }
    System.out.println("arr2 = " + Arrays.toString(arr2) + "\n");
    for (int i : arr2) {
        System.out.println(arr2[i]);
    }

    int arr3Length = 10;
    boolean[] arr3 = new boolean[arr3Length];
    for (int i = random.nextInt(2); i < arr3.length; i+=2) {
        arr3[i] = true;
    }
    System.out.println("arr3 = " + Arrays.toString(arr3) + "\n");
}