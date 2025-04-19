import java.io.*;
import java.util.*;

public class baekjoon14595 {
  static int N;
  static int M;

  static int[] parents;

  static void make() {
    parents = new int[N + 1];

    for(int i =  1; i <= N ; i++) {
      parents[i] = i;
    }
  }

  static int find(int a) {
    if(a == parents[a]) {
      return a;
    }

    return parents[a] = find(parents[a]);
  }

  static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    parents[rootB] = rootA;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    make(); // making rooms

    ArrayList<int[]> work = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int startIdx = Integer.parseInt(st.nextToken());
      int endIdx = Integer.parseInt(st.nextToken());

      // Need to make into array
      // sort and merge overlap
      if(work.isEmpty()) {
        work.add(new int[]{startIdx, endIdx});
      } else {
        boolean isDone = false;
        out: for(int j = 0; j < work.size(); j++) {
          // case : input completely inside of the box
          if(work.get(j)[0] <= startIdx && endIdx <= work.get(j)[1]) {
            isDone = true;
            break out;
          }
          // cse : input completely outside of the box 
          else if(startIdx <= work.get(j)[0] && work.get(j)[1] <= endIdx) {
            work.get(j)[0] = startIdx;
            work.get(j)[1] = endIdx;
            isDone = true;
            break out;
          }
          // case : right side partially overlaps with range
          else if(startIdx < work.get(j)[0] && work.get(j)[0] <= endIdx && endIdx <= work.get(j)[1]) {
            work.get(j)[0] = startIdx;
            isDone = true;
            break out;
          } 
          // case : only left side overlaps the box
          else if(work.get(j)[0] <= startIdx && startIdx <= work.get(j)[1] && work.get(j)[1] < endIdx) {
            work.get(j)[1] = endIdx;
            isDone = true;
            break out;
          }
        }

        if(!isDone) {
          work.add(new int[]{startIdx, endIdx});
        }
      }
    }



    // do union-find
    for(int i = 0 ; i < work.size(); i++) {
      for(int j = work.get(i)[0] ; j < work.get(i)[1] ; j++) {
        union(i, j);
      }
    }

    int ans = 0 ;
    for(int i = 1; i <= N ; i++) {
      if(parents[i] == i) {
        ans += 1;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
