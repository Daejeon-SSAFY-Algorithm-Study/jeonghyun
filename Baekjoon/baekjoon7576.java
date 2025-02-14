import java.util.*;
import java.io.*;

public class baekjoon7576 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];
        q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    int[] c = { i, j, 0 };
                    q.add(c);
                }
            }
        }

        // SOLUTION
        int days = 0;
        while (!q.isEmpty()) {
            int[] curr = q.remove();
            visited[curr[0]][curr[1]] = true;

            days = Math.max(days, curr[2]);

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        map[nx][ny] = 1;
                        int[] c = { nx, ny, curr[2] + 1 };
                        q.add(c);
                    }
                }
            }
        }

        // CHECK MAP
        boolean flag = true;
        out: for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    flag = false;
                    break out;
                }
            }
        }

        if (!flag)
            sb.append(-1).append("\n");
        else
            sb.append(days).append("\n");
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }
}
