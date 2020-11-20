import java.util.concurrent.*;

public class parFibonacci extends RecursiveAction {
    int n, result;

    public parFibonacci(int _n, int _r) {
        n = _n;
        result = _r;
    }

    public void compute() {
        if (n < 2) {
            this.result = n;
            return;
        }

        parFibonacci left = new parFibonacci(this.n - 1, this.result);
        parFibonacci right = new parFibonacci(this.n - 2, this.result);

        left.fork();
        right.compute();
        left.join();

        this.result = left.result + right.result;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(2);
        parFibonacci task = new parFibonacci(40, 0);
        pool.invoke(task);
        System.out.println("Sum = " + task.result);
    }
}
