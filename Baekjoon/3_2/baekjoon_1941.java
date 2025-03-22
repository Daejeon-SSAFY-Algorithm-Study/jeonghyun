import java.util.*;
import java.io.*;

public class baekjoon_1941 {
  static char[][] map;
  static int[] students;
  static boolean[] visited;
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {-1, 1, 0, 0};
  static int ans;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();

    map = new char[5][5];
    students = new int[7];

    for (int i = 0; i < 5; i++) {
      map[i] = br.readLine().toCharArray();
    }

    ans = 0;
    backtracking(0, 0, 0);

    sb.append(ans).append("\n");
    System.out.println(sb.toString());

    br.close();
    bw.flush();
    bw.close();
  }

  private static void backtracking(int count, int YCount, int startIdx) {
    if (YCount >= 4) {
      return;
    }

    if(count == 7) {

      visited = new boolean[7];
      bfs();
      return;
    }

    for(int i = startIdx; i < 25; i++) {
      students[count] = i;
      if(map[i/5][i%5] == 'Y') {
        backtracking(count + 1, YCount + 1, i + 1);
      } else {
        backtracking(count + 1, YCount, i + 1);
      }
    }
  }

  private static void bfs() {
    Queue<int[]> q = new ArrayDeque<>();

    // put in the first student
    q.add(new int[]{students[0]/5, students[0]%5});
    visited[0] = true;
    int count = 1;

    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int currX = curr[0];
      int currY = curr[1];

      for(int i = 0 ; i < 4; i++) {
        int nx = currX + dx[i];
        int ny = currY + dy[i];

        if(!isValid(nx, ny)) {
          continue;
        }

        int nextPos = nx * 5 + ny;

        for(int j = 0 ; j < 7; j++) {
          if(!visited[j] && students[j] == nextPos) {
            q.add(new int[]{nx, ny});
            visited[j] = true;
            count++;
          }
        }
      }
    }
    if(count == 7)
      ans += 1;
  }

  private static boolean isValid(int x, int y) {
    return 0 <= x && x < 5 && 0 <= y && y < 5 ? true : false;
  }
}

