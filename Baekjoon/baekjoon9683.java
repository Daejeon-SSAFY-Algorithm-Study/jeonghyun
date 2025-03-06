import java.util.*;
import java.io.*;

public class baekjoon9683 {
  static int N;
  static int ans;
  static int queens[];

  private static boolean isValid(int cnt) {
    for (int i = 0; i < cnt; i++) {
      if (queens[cnt] == queens[i]) {
        return false;
      } else if (Math.abs(cnt - i) == Math.abs(queens[cnt] - queens[i])) {
        return false;
      }
    }

    return true;
  }

  private static void solve(int cnt) {
    // RETURN CONDITION
    if (cnt == N) {
      ans++;
      return;

    }
    // Algorithm
    for (int i = 0; i < N; i++) {
      queens[cnt] = i;
      if (isValid(cnt)) {
        solve(cnt + 1);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    // INIT
    N = Integer.parseInt(st.nextToken());
    queens = new int[N];
    ans = 0;

    solve(0);

    sb.append(ans).append("\n");
    bw.write(sb.toString());

    br.close();
    bw.flush();
    bw.close();

  }
}
