import java.io.*;
import java.util.*;

public class baekjoon_1654 {
  static int K, N;
  static int[] pipes;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    K = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    pipes = new int[K];
    for (int i = 0; i < K; i++) {
      pipes[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(pipes);
    long high = pipes[pipes.length - 1];
    long low = 1;

    // ALGORITHM
    long solution = 0;
    while (low <= high) {
      long mid = low + (high - low) / 2;

      long count = 0;
      for (int i = 0; i < K; i++) {
        count += pipes[i] / mid;
      }

      if (count >= N) {
        low = mid + 1;
        solution = Math.max(solution, mid);
      } else if (count < N) {
        high = mid - 1;
      }
    }

    System.out.println(solution);

    br.close();
  }
}
