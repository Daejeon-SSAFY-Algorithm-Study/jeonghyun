import java.util.*;
import java.io.*;

/**
 * baekjoon_2630 색종이 만들기
 */
public class baekjoon_2630 {
  static int N;
  static int[][] map;
  static boolean[][] cutted;
  static int countOne, countZero;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    cutted = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    countOne = 0;
    countZero = 0;

    // algorithm
    divide_and_conquer(0, 0, N);

    sb.append(countZero).append("\n")
        .append(countOne).append("\n");

    System.out.print(sb.toString());
    br.close();
    bw.flush();
    bw.close();
  }

  private static void divide_and_conquer(int x, int y, int cut_size) {
    int standard = map[x][y];
    boolean flag = true;
    out: for (int i = x; i < x + cut_size; i++) {
      for (int j = y; j < y + cut_size; j++) {
        if (map[i][j] != standard) {
          flag = false;
          break out;
        }
      }
    }

    int interval = cut_size / 2;
    if (!flag) {
      for (int i = x; i < x + cut_size; i += interval) {
        for (int j = y; j < y + cut_size; j += interval) {
          if (!cutted[i][j])
            divide_and_conquer(i, j, interval);

        }
      }
    } else {
      if (standard == 1) {
        countOne += 1;
      } else {
        countZero += 1;
      }

      for (int i = x; i < x + cut_size; i++) {
        for (int j = y; j < y + cut_size; j++) {
          cutted[i][j] = true;
        }
      }
    }
  }
}
