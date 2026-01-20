void main() {
    int[] numbers = {7, 3, 2, 4, 5, 3};
    boolean isSorted = false;

    do {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                int temp = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = temp;
            }else isSorted = true;
        }
    }while (!isSorted);

    System.out.println(Arrays.toString(numbers));
}