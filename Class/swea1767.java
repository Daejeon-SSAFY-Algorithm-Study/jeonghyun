import java.util.*;
import java.io.*;

class swea1767 {
  static int[] dx = { 0, 0, -1, 1 };
  static int[] dy = { -1, 1, 0, 0 };
  static int N = 0;
  static int minWireLength;
  static int maxProcessors;
  static ArrayList<Processor> processors;
  static int[][] map;

  static class Processor {
    int x;
    int y;

    public Processor(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void solution(int idx, int processorCount, int wireCount) {
    if (idx == processors.size()) {
      if (processorCount > maxProcessors) {
        maxProcessors = processorCount;
        minWireLength = wireCount;
      } else if (processorCount == maxProcessors) {
        minWireLength = Math.min(minWireLength, wireCount);
      }
      return;
    }

    // GET PROCESSOR
    int x = processors.get(idx).x;
    int y = processors.get(idx).y;

    for (int i = 0; i < 4; i++) {
      int nx = x;
      int ny = y;
      int wire = 0;

      while (true) {
        nx += dx[i];
        ny += dy[i];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
          break;
        }

        if (map[nx][ny] == 1) {
          wire = 0;
          break;
        }
        wire++;
      }

      nx = x;
      ny = y;

      for (int j = 0; j < wire; j++) {
        nx += dx[i];
        ny += dy[i];

        map[nx][ny] = 1;
      }
      if (wire > 0) {
        solution(idx + 1, processorCount + 1, wireCount + wire);
      } else {
        solution(idx + 1, processorCount, wireCount);
      }

      // BACK TO ORIGINAL MAP
      nx = x;
      ny = y;

      for (int j = 0; j < wire; j++) {
        nx += dx[i];
        ny += dy[i];
        map[nx][ny] = 0;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    int in = Integer.parseInt(st.nextToken());

    for (int testCase = 1; testCase <= in; testCase++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      processors = new ArrayList<>();

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());

        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());

          if (map[i][j] == 1) {
            if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
              processors.add(new Processor(i, j));
            }
          }
        }
      }

      minWireLength = Integer.MAX_VALUE;
      maxProcessors = Integer.MIN_VALUE;

      solution(0, 0, 0);

      // RECORD ANSWER
      sb.append("#").append(testCase).append(" ").append(minWireLength).append("\n");
    }
    bw.write(sb.toString());

    br.close();
    bw.flush();
    bw.close();

  }

}
