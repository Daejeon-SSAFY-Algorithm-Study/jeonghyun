import java.util.*;
import java.io.*;

public class baekjoon_2638 {
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    visited = new boolean[N][M];
    int cheeseCount = 0;
    // Receiving input for map
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());

        if(map[i][j] == 1) {
          cheeseCount += 1;
        }
      }
    }

    // Algorithm
    int time = 0;
    Queue<int[]> q = new ArrayDeque<>();

    while(cheeseCount != 0) {
      time += 1;
      visited = new boolean[N][M]; // needs to refresh everytime
      int[][] airExposure = new int[N][M];

      q.add(new int[]{0,0});
      visited[0][0] = true;
      
      while(!q.isEmpty()) {
        int[] current = q.poll();

        for(int i = 0 ; i < 4; i++) {
          int nx = current[0] + dx[i];
          int ny = current[1] + dy[i];

          if(0 <= nx && nx < N && 0 <= ny && ny < M) {
            if(map[nx][ny] == 0 && !visited[nx][ny]) {
              q.add(new int[]{nx, ny});
              visited[nx][ny] = true;
            } else if(map[nx][ny] == 1) {
              airExposure[nx][ny]++;
            }
          }
        }
      }
      
      // Melt cheese cells exposed to air on at least 2 sides
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
          if(map[i][j] == 1 && airExposure[i][j] >= 2) {
            map[i][j] = 0;
            cheeseCount--;
          }
        }
      }
    }

    sb.append(time).append("\n");

    bw.write(sb.toString());

    br.close();
    bw.flush();
    bw.close();
  }

}
