import java.util.*;
import java.io.*;

public class baekjoon2578 {

  static boolean countZeros(int[] h_count, int[] v_count, int up_diag_count, int down_diag_count) {
    int count = 0;
    for (int i = 0; i < h_count.length; i++) {
      if (h_count[i] == 0)
        count++;
    }
    for (int i = 0; i < v_count.length; i++) {
      if (v_count[i] == 0)
        count++;
    }
    if (up_diag_count == 0)
      count++;
    if (down_diag_count == 0)
      count++;

    if (count == 3) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();

    ArrayList<Integer> board = new ArrayList<>();

    // RECEIVING INPUT FOR BOARD
    for (int i = 0; i < 5; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      while (st.hasMoreTokens()) {
        board.add(Integer.parseInt(st.nextToken()));
      }
    }

    int called = 0;

    int[] row_count = { 5, 5, 5, 5, 5 };
    int[] col_count = { 5, 5, 5, 5, 5 };
    int up_diag_count = 5;
    int down_diag_count = 5;

    out: for (int i = 0; i < 5; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      while (st.hasMoreTokens()) {
        int current = Integer.parseInt(st.nextToken());
        called++;

        if (board.contains(current)) {
          int idx = board.indexOf(current);

          int row = idx / 5;
          int col = idx % 5;

          row_count[row] -= 1;
          if (row_count[row] == 0) {
            if (countZeros(row_count, col_count, up_diag_count, down_diag_count)) {
              sb.append(called).append("\n");
              break out;
            }
          }
          col_count[col] -= 1;
          if (col_count[col] == 0) {
            if (countZeros(row_count, col_count, up_diag_count, down_diag_count)) {
              sb.append(called).append("\n");
              break out;
            }
          }
          if (row == col) {
            up_diag_count -= 1;
          }
          if (up_diag_count == 0) {
            if (countZeros(row_count, col_count, up_diag_count, down_diag_count)) {
              sb.append(called).append("\n");
              break out;
            }
          }

          if (row + col == 4) {
            down_diag_count -= 1;
          }
          if (down_diag_count == 0) {
            if (countZeros(row_count, col_count, up_diag_count, down_diag_count)) {
              sb.append(called).append("\n");
              break out;
            }
          }

        }
      }
    }
    // out to here

    // PRINT ANSWER
    bw.write(sb.toString());
    br.close();
    bw.flush();
    bw.close();
  }

}
