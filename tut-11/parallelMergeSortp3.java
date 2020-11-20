import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class parallelMergeSortp3 extends RecursiveAction {
  private final int[] array;
  private final int[] helper;
  private final int low;
  private final int high;

  public parallelMergeSortp3(final int[] array, final int low, final int high) {
    this.array = array;
    helper = new int[array.length];
    this.low = low;
    this.high = high;
  }

  @Override
  protected void compute() {
    if (low < high) {
      final int middle = (low + high) / 2;
      final parallelMergeSortp3 left = new parallelMergeSortp3(array, low, middle);
      final parallelMergeSortp3 right = new parallelMergeSortp3(array, middle + 1, high);
      invokeAll(left, right);
      merge(array, helper, low, middle, high);
    }
  }

  static void merge(final int[] array, final int[] helper, final int low, final int middle, final int high) {
    for (int i = low; i <= high; i++) {
      helper[i] = array[i];
    }

    int helperLeft = low;
    int helperRight = middle + 1;
    int current = low;

    while (helperLeft <= middle && helperRight <= high) {
      if (helper[helperLeft] <= helper[helperRight]) {
        array[current] = helper[helperLeft++];
      } else {
        array[current] = helper[helperRight++];
      }
      current++;
    }

    while (helperLeft <= middle) {
      array[current++] = helper[helperLeft++];
    }
  }

  private static void check(final int[] array) {
    int last = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      if (array[i] < last) {
        System.out.println("Array not sorted");
      }
      last = array[i];
    }
  }

  static int[] random(final int n) {
    final int[] a = new int[n];

    for (int i = 0; i < n; i++) {
      a[i] = new Random().nextInt(n);
    }

    return a;
  }

  public static void testParallel() {
    final int[] array = random(1000);
    final ForkJoinPool forkJoinPool = new ForkJoinPool(7);
    forkJoinPool.invoke(new parallelMergeSortp3(array, 0, array.length - 1));

    check(array);
  }

  public static void main(String[] args) {
    testParallel();
  }

}
