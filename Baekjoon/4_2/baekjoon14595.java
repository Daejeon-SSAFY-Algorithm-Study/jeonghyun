import java.io.*;
import java.util.*;

public class baekjoon14595 {
  static int N;
  static int M;

  static int[] parents;

  static class Room implements Comparable<Room> {
    int front;
    int back;

    public Room(int front, int back) {
      this.front = front;
      this.back = back;
    }

    /*
     * if current element's leftmost is smaller than comparing element, put smaller
     * leftmost to front
     * if leftmost same, then compare rightmost and put smaller rightmost to front
     */
    @Override
    public int compareTo(Room o) {
      if (this.front > o.front) {
        return 1;
      } else if (this.front < o.front) {
        return -1;
      } else if (this.back < o.back) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  static void make() {
    parents = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      parents[i] = i;
    }
  }

  static int find(int a) {
    if (a == parents[a]) {
      return a;
    }

    return parents[a] = find(parents[a]);
  }

  static void union(int front, int back) {
    int rootFront = find(front);
    int rootBack = find(back);

    if (rootFront != rootBack) {
      parents[rootBack] = rootFront;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PriorityQueue<Room> pq = new PriorityQueue<>();

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    make(); // making rooms

    for (int i = 0; i < M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int startIdx = Integer.parseInt(st.nextToken());
      int endIdx = Integer.parseInt(st.nextToken());

      pq.add(new Room(startIdx, endIdx));

    }

    int rightmost = 0;
    while (!pq.isEmpty()) {
      Room curr = pq.poll();

      int front = curr.front;
      int back = curr.back;

      // Use smaller leftmost
      if (front < rightmost) {
        front = rightmost;
      }

      for (int i = front; i <= back; i++) {
        union(front, i);
      }

      // update rightmost if current rightmost is bigger
      if (back > rightmost) {
        rightmost = back;
      }

    }

    int ans = 0;
    for (int i = 1; i <= N; i++) {
      if (parents[i] == i) {
        ans += 1;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
