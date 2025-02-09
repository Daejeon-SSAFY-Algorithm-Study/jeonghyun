import java.util.*;
import java.io.*;

class baekjoon_1932 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[][] input = new int[N][N];

    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < i; j++) {
        input[i - 1][j] = Integer.parseInt(st.nextToken());
      }
    }

    StringBuilder sb = new StringBuilder();

    // ALGORITHM
    int[][] arr = new int[N][N];

    arr[0][0] = input[0][0];

    int ans = 0;
    if (N == 1) {
      ans = input[0][0];
    } else {
      for (int i = 1; i < N; i++) {
        for (int j = 0; j < i + 1; j++) {
          // case: 맨앞에 있는 경우, front j, j+1
          if (j == 0) {
            arr[i][j] = arr[i - 1][j] + input[i][j];
          }
          // case : 각 줄의 끝, j와 i의 index 같음
          else if (j == i) {
            arr[i][j] = arr[i - 1][j - 1] + input[i][j];
          } else {
            arr[i][j] = Math.max(arr[i - 1][j - 1] + input[i][j], arr[i - 1][j] + input[i][j]);
          }

          // only at last row get answer
          if (i == N - 1) {
            ans = Math.max(ans, arr[i][j]);
          }
        }
      }
    }

    sb.append(ans).append("\n");

    bw.write(sb.toString());
    br.close();
    bw.flush();
    bw.close();
  }
}