import java.io.*;
import java.util.*;

public class baekjoon_20208 {
  static int N, M, H;
  static int[][] map;
  static int[][] mint;

  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    mint = new int[10][2];

    // Initialize map for receiving input
    int startX = -1, startY = -1;
    int mintCount = 0;
    int idx = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine().trim());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          startX = i;
          startY = j;
        } else if (map[i][j] == 2) {
          mint[idx][0] = i;
          mint[idx][1] = j;
          idx++;
        }
      }
    }

    // solve using backtracking

    backtracking()

  }

}
