import java.io.*;
import java.util.*;

public class baekjoon1987 {
  static int R, C;
  static char[][] board;
  static int ans;
  static boolean[] visited; // Changed to track alphabets directly
  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    // Initialize
    board = new char[R][C];
    visited = new boolean[26]; // length = number of alphabets
    ans = 1; // always start with 1

    for (int i = 0; i < R; i++) {
      String line = br.readLine();
      for (int j = 0; j < C; j++) {
        board[i][j] = line.charAt(j);
      }
    }

    // Mark start point to visited
    visited[board[0][0] - 'A'] = true;
    dfs(0, 0, 1);

    bw.write(String.valueOf(ans));

    br.close();
    bw.flush();
    bw.close();
  }

  private static void dfs(int x, int y, int count) {
    ans = Math.max(ans, count);

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
        int alphabetIndex = board[nx][ny] - 'A';
        
        if (!visited[alphabetIndex]) {
          visited[alphabetIndex] = true;
          dfs(nx, ny, count + 1);
          visited[alphabetIndex] = false; // Backtrack
        }
      }
    }
  }
}
