import java.io.*;
import java.util.*;

public class baekjoon_2110 {
  static int N, C, maxDistance;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    int[] houses = new int[N];
    for (int i = 0; i < N; i++) {
      houses[i] = Integer.parseInt(br.readLine());
    }

    // ALGORITHM
    // use 이분탐색

    Arrays.sort(houses);

    int low = 1;
    int high = houses[N - 1] - houses[0];
    int maxDistance = 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int count = 1;

      int lastHouse = houses[0];
      for(int i = 1; i < N; i++) {
        if(houses[i] - lastHouse >= mid) {
          count++;
          lastHouse = houses[i];
        }
      }

      if(count >= C) {
        low = mid + 1;
        maxDistance = Math.max(maxDistance, mid);
      } else {
        high = mid - 1;
      }
    }

    System.out.println(maxDistance);
    br.close();
  }
}
