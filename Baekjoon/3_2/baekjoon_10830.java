import java.util.*;
import java.io.*;

public class baekjoon_10830 {
  static int N;
  static long B;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    B = Long.parseLong(st.nextToken());

    long[][] base = new long[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        base[i][j] = Long.parseLong(st.nextToken()) % 1000;
      }
    }

    long[][] result = base.clone();

    for (int i = 0; i < B - 1; i++) {
      result = matrixMultiplication(result, base);
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        result[i][j] = result[i][j] % 1000;
        sb.append(result[i][j]).append(" ");
      }
      sb.append("\n");
    }

    System.out.println(sb.toString());

    br.close();
  }

  private static long[][] matrixMultiplication(long[][] input, long[][] base) {
    long[][] result = new long[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          result[i][j] += bitwiseMultiply(input[i][k], base[k][j]);
          result[i][j] = result[i][j] % 1000; // ADDED
        }
      }
    }

    return result;
  }

  private static long bitwiseMultiply(long a, long b) {
    long result = 0;

    while (b > 0) {
      if ((b & 1) == 1) {
        result += a;
      }

      a <<= 1;
      b >>= 1;
    }

    return result;
  }
}
