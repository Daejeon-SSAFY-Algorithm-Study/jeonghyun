import java.io.*;
import java.util.*;

public class baekjoon_12865 {
  static int N, K, W, V;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[] weights = new int[N + 1];
    int[] values = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());

      W = Integer.parseInt(st.nextToken());
      V = Integer.parseInt(st.nextToken());

      weights[i] = W;
      values[i] = V;

    }

    int[][] dp = new int[N + 1][K + 1];
    for (int i = 1; i <= N; i++) {
      for (int w = 1; w <= K; w++) {
        if (weights[i] <= w) {
          dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i]] + values[i]);
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }

    System.out.println(dp[N][K]);

    br.close();
  }
}
