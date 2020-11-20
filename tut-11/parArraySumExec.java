import java.util.concurrent.*;

public class parArraySumExec implements Runnable {
    int[] array;
    int sum, low, high;

    public parArraySumExec(int[] arr, int l, int h) {
        array = arr;
        sum = 0;
        low = l;
        high = h;
    }

    public void run() {
        for(int i = low; i < high; i++) {
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

        ExecutorService exec = Executors.newFixedThreadPool(2);

        parArraySumExec left = new parArraySumExec(array, 0, size / 2);
        parArraySumExec right = new parArraySumExec(array, size / 2, size);

        exec.execute(left);
        exec.execute(right);

        if (!exec.isTerminated()) {
            exec.shutdown();
            exec.awaitTermination(5L, TimeUnit.SECONDS);
        }

        int result = left.getResult() + right.getResult();

        System.out.println("Sum = " + result);
    }
}
