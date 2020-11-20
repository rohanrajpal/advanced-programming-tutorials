public class sequentialMergeSort {
  public static void main(String[] args) {
    final int size = args.length > 0 ? Integer.parseInt(args[0]) : 20_000_000;
    final int threshold = args.length > 1 ? Integer.parseInt(args[1]) : 500_000;

    java.util.Random rand = new java.util.Random(0);
    final int[] A = new int[size];
    final int[] buff = new int[size]; // buffer used for copying during merge step

    for (int i = 0; i < size; i++) {
      A[i] = rand.nextInt();
    }

    final long seqStartTime = System.nanoTime();
    mergesort(A, 0, size - 1, size, buff);
    final long seqExecTime = System.nanoTime() - seqStartTime;

    if (valid(A)) {
      System.out.println("Sequential sort completed successfully in " + seqExecTime / 1e6 + " milliseconds");
    } else {
      System.out.println("Sequential sort failed\n");
    }
  }

  private static void mergesort(final int[] A, final int M, final int N, final int threshold, final int[] buff) {
    if (M < N) {
      final int mid = M + (N - M) / 2;

      mergesort(A, M, mid, threshold, buff);
      mergesort(A, mid + 1, N, threshold, buff);
      merge(A, M, mid, N, buff);
    }
  }

  private static void merge(final int[] A, final int M, final int mid, final int N, final int[] buff) {
    // make a copy in buff[M:N]
    for (int k = M; k <= N; k++) {
      buff[k] = A[k];
    }

    // merge from buff to A
    int i = M;
    int j = mid + 1;
    for (int k = M; k <= N; k++) {
      if (i > mid) {
        A[k] = buff[j++];
      } else if (j > N) {
        A[k] = buff[i++];
      } else if (buff[j] < buff[i]) {
        A[k] = buff[j++];
      } else {
        A[k] = buff[i++];
      }
    }
  }

  private static boolean valid(final int[] A) {
    for (int i = 1; i < A.length; i++) {
      if (A[i] < A[i - 1]) {
        return false;
      }
    }
    return true;
  }
}
