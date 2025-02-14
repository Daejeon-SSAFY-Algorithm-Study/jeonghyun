package Week2;

import java.util.*;
import java.io.*;

public class baekjoon1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] map = new int[100003];
        Arrays.fill(map, 0);
        boolean[] visited = new boolean[100003];
        Arrays.fill(visited, false);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { N, 0 });

        int[] dx = { -1, 1 };
        ArrayList<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int idx = curr[0];

            if (idx == K) {
                ans.add(curr[1]);
            }

            for (int i = 0; i < 3; i++) {
                int nx = 0;

                // For case: n * 2
                if (i == 2) {
                    nx = idx * 2;
                    if (0 <= nx && nx < 100002 && !visited[nx]) {
                        q.add(new int[] { nx, curr[1] + 1 });
                        visited[nx] = true;
                    }
                } else { // case : n + 1 or n - 1
                    nx = idx + dx[i];
                }

                // check if it is valid
                if (0 <= nx && nx < 100002 && !visited[nx]) {
                    q.add(new int[] { nx, curr[1] + 1 });
                    visited[nx] = true;
                }
            }
        }

        Collections.sort(ans);

        sb.append(ans.get(0)).append("\n");

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
