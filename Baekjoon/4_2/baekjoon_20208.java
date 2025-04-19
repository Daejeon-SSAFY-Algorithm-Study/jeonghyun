import java.io.*;
import java.util.*;

public class baekjoon_20208 {
  static int N, M, H;
  static int[][] map;
  static int[][] mint;
  static int startX, startY, count;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    mint = new int[10][2];

    // Initialize map for receiving input
    int mintCount = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine().trim());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          startX = i;
          startY = j;
        } else if (map[i][j] == 2) {
          mint[mintCount][0] = i;
          mint[mintCount][1] = j;
          mintCount++;
        }
      }
    }

    // excluding
    for (int i = mintCount; i < 10; i++) {
      mint[i][0] = -1;
    }

    // solve using backtracking
    count = 0;
    boolean[] visited = new boolean[mintCount];
    dfs(startX, startY, M, 0, visited, mintCount);

    System.out.println(count);
    br.close();
  }

  public static void dfs(int x, int y, int health, int collected, boolean[] visited, int mintCount) {
    // Try to go back home and update max count
    int distanceToHome = Math.abs(x - startX) + Math.abs(y - startY);
    if (health >= distanceToHome) {
      count = Math.max(count, collected);
    }

    // Try to collect more mints
    for (int i = 0; i < mintCount; i++) {
      if (!visited[i] && mint[i][0] != -1) {
        int nx = mint[i][0];
        int ny = mint[i][1];
        int distance = Math.abs(x - nx) + Math.abs(y - ny);
        
        // Check if we can reach this mint
        if (health >= distance) {
          visited[i] = true;
          dfs(nx, ny, health - distance + H, collected + 1, visited, mintCount);
          visited[i] = false;
        }
      }
    }
  }
}
