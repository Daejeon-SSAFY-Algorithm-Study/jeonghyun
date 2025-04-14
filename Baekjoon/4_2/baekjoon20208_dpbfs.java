import java.util.*;
import java.io.*;

public class baekjoon20208 {
  static int N, M, H, ans;
  static int[][] map;
  static int[][] dp;

  static class Node {
    int x, y, health;
    boolean[] visited;

    public Node(int x, int y, int health, boolean[] visited) {
      this.x = x;
      this.y = y;
      this.health = health;
      this.visited = visited;
    }
  }

  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { -1, 1, 0, 0 };

  // array to store whether mint has been visited
  static ArrayList<int[]> mint;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    int startX = 0, startY = 0;
    mint = new ArrayList<>();
    map = new int[N][N];
    dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          startX = i;
          startY = j;
          dp[i][j] = M;
        } else if (map[i][j] == 2) {
          mint.add(new int[] { i, j });
        }
      }
    }
    ans = Integer.MIN_VALUE;

    Queue<Node> q = new LinkedList<>();

    q.add(new Node(startX, startY, M, new boolean[mint.size()]));

    out: while (!q.isEmpty()) {
      // get current element
      Node curr = q.poll();
      int x = curr.x;
      int y = curr.y;
      int health = curr.health;
      boolean[] currVisited = curr.visited;

      // get next element in 4 directions
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // check if it is not out of bounds
        if (nx < 0 || nx >= N || ny < 0 || ny >= N)
          continue;

        // check next element
        // back to original position, skip
        if (nx == startX && ny == startY) {
          continue;
        }
        // if it is a road
        else if (map[nx][ny] == 0) {
          // check if can come back to start position
          int nextHealth = health - 1;
          if (nextHealth >= 0) {
            dp[nx][ny] = nextHealth;
            q.add(new Node(nx, ny, nextHealth, currVisited));
          }
        } else if (map[nx][ny] == 2) {
          // when it is mint
          for (int j = 0; j < mint.size(); j++) {
            // check if it is already visited
            if (mint.get(j)[0] == nx && mint.get(j)[1] == ny) {
              if (!currVisited[j]) {
                boolean[] nextVisited = Arrays.copyOf(currVisited, currVisited.length);
                nextVisited[j] = true;
                int nextHealth = health - 1 + H;

                // check if it can go to start position
                int distance = Math.abs(nx - startX) + Math.abs(ny - startY);
                if (nextHealth >= distance && nextHealth >= dp[nx][ny]) {
                  dp[nx][ny] = nextHealth;
                  q.add(new Node(nx, ny, nextHealth, nextVisited));

                  // update ans
                  int cnt = 0;
                  for (int k = 0; k < nextVisited.length; k++) {
                    if (nextVisited[k]) {
                      cnt++;
                    }
                  }

                  ans = Math.max(ans, cnt);

                  if (ans == mint.size()) {
                    break out;
                  }
                }
              }
            }
          }
        }

      }

    }

    System.out.println(ans);

    br.close();
  }
}
