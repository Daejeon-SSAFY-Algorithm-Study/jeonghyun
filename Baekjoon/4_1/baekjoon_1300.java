import java.io.*;

public class baekjoon_1300 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    long low = 1;
    long high = (long) N * N;
    long ans = 0;

    while (low <= high) {
      long mid = low + (high - low) / 2;
      long count = 0;

      for (int i = 1; i <= N; i++) {
        count += Math.min(mid / i, N);
      }

      if (count < K) {
        low = mid + 1;
      } else {
        ans = mid;
        high = mid - 1;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
