import java.util.concurrent.*;

public class parFibonacciReturn extends RecursiveTask<Integer> {
    int n;

    public parFibonacciReturn(int _n) {
        n = _n;
    }

    public Integer compute() {
        if (n < 2) {
            return n;
        }

        parFibonacciReturn left = new parFibonacciReturn(this.n - 1);
        parFibonacciReturn right = new parFibonacciReturn(this.n - 2);

        left.fork();
        return right.compute() + left.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(2);
        parFibonacciReturn task = new parFibonacciReturn(40);
        int result = pool.invoke(task);

        System.out.println("Sum = " + result);
    }
}
