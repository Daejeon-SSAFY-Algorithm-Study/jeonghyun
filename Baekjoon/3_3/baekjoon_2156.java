import java.io.*;
import java.util.*;

public class baekjoon_2156 {
  static int N;
  static int[] wines;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    wines = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      wines[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[N + 1];

    if (N <= 2) {

    } else {

    }

    System.out.println();

    br.close();
  }
}
