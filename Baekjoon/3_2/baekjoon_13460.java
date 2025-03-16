import java.io.*;
import java.util.*;

public class baekjoon_13460 {
  static int N, M;
  static char[][] map;
  static int ans;
  static int[] dx = { 0, 0, -1, 1 }; // Left Right Down Up
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dest;
  static boolean[][][][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new char[N][M];
    visited = new boolean[N][M][N][M];

    // Initialize map
    int[] pos = new int[4];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j);

        if (map[i][j] == 'R') {
          pos[0] = i;
          pos[1] = j;
        } else if (map[i][j] == 'B') {
          pos[2] = i;
          pos[3] = j;
        }
      }
    }
    ans = -1;

    // Algorithm
    bfs(pos, 0);
    sb.append(ans).append("\n");
    bw.write(sb.toString());

    br.close();
    bw.flush();
    bw.close();
  }

  private static void bfs(int[] pos, int count) {
    Queue<int[]> q = new ArrayDeque<>();
    int[] element = new int[] { pos[0], pos[1], pos[2], pos[3], count };

    q.add(element);
    visited[pos[0]][pos[1]][pos[2]][pos[3]] = true;

    while (!q.isEmpty()) {
      int[] curr = q.poll();

      if (curr[4] >= 10) {
        return;
      }

      for (int i = 0; i < 4; i++) {
        int[] posRed = move(curr[0], curr[1], i);
        int[] posBlue = move(curr[2], curr[3], i);

        if (map[posBlue[0]][posBlue[1]] == 'O')
          continue;
        if (map[posRed[0]][posRed[1]] == 'O') {
          ans = (ans == -1) ? curr[4] + 1 : Math.min(ans, curr[4] + 1);
          return;
        }

        if (posBlue[0] == posRed[0] && posBlue[1] == posRed[1]) {
          if (posBlue[2] > posRed[2]) {
            posBlue[0] -= dx[i];
            posBlue[1] -= dy[i];
          } else {
            posRed[0] -= dx[i];
            posRed[1] -= dy[i];
          }
        }

        if (!visited[posRed[0]][posRed[1]][posBlue[0]][posBlue[1]]) {
          visited[posRed[0]][posRed[1]][posBlue[0]][posBlue[1]] = true;
          q.add(new int[] { posRed[0], posRed[1], posBlue[0], posBlue[1], curr[4] + 1 });

        }
      }
    }

  }

  private static int[] move(int x, int y, int dir) {
    int currX = x;
    int currY = y;
    int count = 0;

    while (map[currX + dx[dir]][currY + dy[dir]] != '#' && map[currX][currY] != 'O') {
      currX += dx[dir];
      currY += dy[dir];

      count++;
    }

    return new int[] { currX, currY, count };
  }

}
