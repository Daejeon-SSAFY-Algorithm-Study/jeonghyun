import java.io.*;
import java.util.*;

public class baekjoon_2156 {
  static int N;
  static int[] wines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    wines = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      wines[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[N + 1];

    // Handle edge cases
    if (N >= 1)
      dp[1] = wines[1];
    if (N >= 2)
      dp[2] = wines[1] + wines[2];

    for (int i = 3; i <= N; i++) {
      dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
    }

    System.out.println(dp[N]);

    br.close();
  }
}
