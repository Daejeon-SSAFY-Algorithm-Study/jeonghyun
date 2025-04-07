import java.util.*;
import java.io.*;

/**
 * baekjoon_2529
 */
public class baekjoon_2529 {
  static int N, desiredNum;
  static char[] arr;
  static long maxNum = Long.MIN_VALUE, minNum = Long.MAX_VALUE;
  static String maxStr, minStr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    arr = new char[N];

    for (int i = 0; i < N; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    desiredNum = N + 1;

    boolean[] visited = new boolean[10];
    int[] numArr = new int[desiredNum];

    for (int i = 0; i <= 9; i++) {
      numArr[0] = i;
      visited[i] = true;
      backtracking(0, numArr, visited);
      visited[i] = false; // reset
    }

    System.out.println(maxStr);
    System.out.println(minStr);

    br.close();
  }

  private static void backtracking(int idx, int[] numArr, boolean[] visited) {
    if (idx == N) {
      checkValue(numArr);

      return;
    }

    for (int i = 0; i <= 9; i++) {
      if (!visited[i]) {
        if (arr[idx] == '<') {
          if (numArr[idx] < i) {
            numArr[idx + 1] = i;
            visited[i] = true;
            backtracking(idx + 1, numArr, visited);
            visited[i] = false;
          }
        } else {
          if (numArr[idx] > i) {
            numArr[idx + 1] = i;
            visited[i] = true;
            backtracking(idx + 1, numArr, visited);
            visited[i] = false;
          }
        }
      }
    }
  }

  private static void checkValue(int[] numArr) {
    String numStr = "";

    for (int i = 0; i < numArr.length; i++) {
      numStr += numArr[i];
    }

    if (Long.parseLong(numStr) > maxNum) {
      maxNum = Long.parseLong(numStr);
      maxStr = String.valueOf(numStr);
    }
    if (Long.parseLong(numStr) < minNum) {
      minNum = Long.parseLong(numStr);
      minStr = String.valueOf(numStr);
    }
  }
}
