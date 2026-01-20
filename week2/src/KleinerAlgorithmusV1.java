/*
    1 + 1 = 2
    1 + 2 = 3
    2 + 3 = 5
    3 + 5 = 8
    5 + 8 = 13
    8 + 13 = 21
* */

void main() {
    int a = 1, b = 1, counter = 6;

    for (int i = 0; i < counter; i++) {
        int c = a + b;
        System.out.println(a + " + " + b + " = " + c);
        a = b;
        b = c;
    }
}

