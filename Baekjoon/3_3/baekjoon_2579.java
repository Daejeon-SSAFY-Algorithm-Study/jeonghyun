import java.io.*;

public class baekjoon_2579 {
  static int N, score;
  static int[] stairs;
  static int[] scores;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());
    stairs = new int[N + 1];
    scores = new int[N + 1];
    score = Integer.MIN_VALUE;

    for (int i = 1; i < N + 1; i++) {
      stairs[i] = Integer.parseInt(br.readLine());
    }

    scores[1] = stairs[1];

    if (N >= 2) {
      scores[2] = Math.max(stairs[2] + scores[1], stairs[2] + scores[0]);
    }

    if (N >= 3) {
      scores[3] = Math.max(stairs[3] + scores[1], stairs[3] + stairs[2] + scores[0]);
      for (int i = 4; i < N + 1; i++) {
        scores[i] = Math.max(stairs[i] + scores[i - 2], stairs[i] + stairs[i - 1] + scores[i - 3]);
      }

    }

    sb.append(scores[N]).append("\n");
    System.out.print(sb.toString());

    br.close();
  }

}
