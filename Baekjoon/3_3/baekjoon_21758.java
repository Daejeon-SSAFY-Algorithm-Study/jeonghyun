import java.util.*;
import java.io.*;

public class baekjoon_21758 {
  static int N, ans;
  static int[] honey;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    honey = new int[N + 2];

    for (int i = 1; i <= N; i++) {
      honey[i] = Integer.parseInt(st.nextToken());
    }

    int[] left_to_right = new int[N + 2];

    for (int i = 1; i <= N; i++) {
      left_to_right[i] = honey[i] + left_to_right[i - 1];
    }

    ans = Integer.MIN_VALUE;

    // home on rightmost, bee on leftmost
    int leftmost = left_to_right[N] - honey[1];

    for (int i = 2; i < N; i++) { // moving other bee to right
      ans = Math.max(ans, leftmost - honey[i] + left_to_right[N] - left_to_right[i]);
    }

    // home on leftmost, bee on rightmost
    int rightmost = left_to_right[N] - honey[N];
    for (int i = N - 1; i > 1; i--) {
      ans = Math.max(ans, rightmost - honey[i] + left_to_right[i - 1]);
    }

    // bees on rightmost, leftmost, moving home
    for (int i = 2; i < N; i++) {
      ans = Math.max(ans, left_to_right[i] - honey[1] + rightmost - left_to_right[i] + honey[i]);
    }

    sb.append(ans).append("\n");
    System.out.print(sb.toString());

    br.close();
  }
}
