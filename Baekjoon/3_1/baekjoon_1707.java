import java.io.*;
import java.util.*;

public class baekjoon_1707 {
  static int V, E;
  static List<List<Integer>> graph;
  static int[] visited;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    for(int tc = 0 ; tc < T ; tc++){
      st = new StringTokenizer(br.readLine());

      V = Integer.parseInt(st.nextToken());

      // Initializing the graph
      graph = new ArrayList<>();
      for(int i = 0 ; i <= V ; i++) { // Initialize with V+1 elements
        graph.add(new ArrayList<>());
      }
      visited = new int[V + 1];

      E = Integer.parseInt(st.nextToken());

      for(int i = 0;  i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        // Adding to both from and to, because it is undirected graph
        graph.get(from).add(to);
        graph.get(to).add(from);
      }

      boolean flag = true;
      for(int i = 1 ; i <= V; i++) {
        if(visited[i] == 0) { 
          if(!checkBipartite(i)) {
            flag = false;
            break;
          }
        }
      }

      if (flag) {
        sb.append("YES").append("\n");
      }
      else {
        sb.append("NO").append("\n");
      }
    }

    bw.write(sb.toString());

    br.close();
    bw.flush();
    bw.close();
  }

  private static boolean checkBipartite(int start) {
    Queue<Integer> q = new ArrayDeque<>();
    q.add(start);

    visited[start] = 1;

    while(!q.isEmpty()) {
      int curr = q.poll();
      
      for(int next : graph.get(curr)) {
        if(visited[next] == visited[curr]) // if has same number, it is not bipartite, return false
          return false;

        if(visited[next] == 0) { // If not visited
          visited[next] = -visited[curr]; // assign negative value to show opposite
          q.add(next);
        }
      }
    }
    return true;
  }
}
