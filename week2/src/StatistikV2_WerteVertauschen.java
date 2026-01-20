void main() {
    int x = 11;
    int y = 10;

    if (x > y) {
        int temp = x;
        x = y;
        y = temp;
    }

    System.out.println("x: " + x + "\ny: " + y);
}