import java.util.*;
import java.io.*;

/**
 * baekjoon_25682 체스판 다시 칠하기 2
 */
public class baekjoon_25682 {
  static int N, M, K;
  static char[][] map;
  static int[][] prefixSum;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new char[N][M];
    prefixSum = new int[N + 1][M + 1];

    for (int i = 0; i < N; i++) {
      map[i] = br.readLine().toCharArray();
    }

    // Calculate minimum recoloring starting with 'B'
    int minRecolor = getMinRecolor('B');

    // Calculate minimum recoloring starting with 'W'
    minRecolor = Math.min(minRecolor, getMinRecolor('W'));

    System.out.println(minRecolor);
    br.close();
  }

  // Method to calculate minimum recoloring with a specific starting color
  private static int getMinRecolor(char startColor) {
    // Build prefix sum array
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        // Determine expected color at current position
        char expectedColor;
        if ((i + j) % 2 == 0) {
          expectedColor = startColor;
        } else {
          expectedColor = startColor == 'B' ? 'W' : 'B';
        }

        // Count cells that need recoloring (1 if mismatch, 0 if match)
        int needsRecolor = (map[i][j] != expectedColor) ? 1 : 0;

        // Calculate prefix sum
        prefixSum[i + 1][j + 1] = prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i][j] + needsRecolor;
      }
    }

    // Find minimum recoloring for all K×K subgrids
    int minCount = Integer.MAX_VALUE;
    for (int i = K; i <= N; i++) {
      for (int j = K; j <= M; j++) {
        // Calculate sum for current K×K subgrid using prefix sum
        int count = prefixSum[i][j] - prefixSum[i - K][j] - prefixSum[i][j - K] + prefixSum[i - K][j - K];
        minCount = Math.min(minCount, count);
      }
    }

    return minCount;
  }
}
