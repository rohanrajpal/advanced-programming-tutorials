public class parArraySumRunnable implements Runnable {
    private int[] array;
    private int sum, low, high;

    public parArraySumRunnable(int[] arr, int l, int h) {
        array = arr;
        sum = 0;
        low = l;
        high = h;
    }

    public void run() {
        for (int i = low; i < high; i++) {
            sum += array[i];
        }
    }

    public int getResult() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int i, size = 50;
        int[] array = new int[50];

        for(i = 0; i < 50; i++) {
            array[i] = i + 1;
        }

        parArraySumRunnable left = new parArraySumRunnable(array, 0, size / 2);
        parArraySumRunnable right = new parArraySumRunnable(array, size / 2, size);

        Thread t1 = new Thread(left);
        Thread t2 = new Thread(right);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int result = left.getResult() + right.getResult();

        System.out.println("Sum = " + result);
    }
}
