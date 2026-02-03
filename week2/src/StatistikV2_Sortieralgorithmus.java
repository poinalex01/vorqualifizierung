void main() {
    Random random = new Random();

    for (int j = 0; j < 1000; j++) {

        int[] numbers = random.ints(1000, 0, 1000).toArray();
        boolean isSorted;
        long t1 = System.nanoTime();

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
        long t2 = System.nanoTime();
        System.out.println(t2 - t1);
    }

}