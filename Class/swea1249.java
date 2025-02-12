import java.io.*;
import java.util.*;

class Data {
    int x;
    int y;

    public Data(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class swea1249 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int in = Integer.parseInt(st.nextToken());

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int testCase = 1; testCase <= in; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            int[][] total = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            // INITIALIZATION
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    total[i][j] = 0;
                    visited[i][j] = false;
                }
            }
            Queue<Data> queue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String lineNumStr = st.nextToken();
                for (int j = 0; j < N; j++) {
                    map[i][j] = lineNumStr.charAt(j) - '0';
                }
            }

            Data data = new Data(0, 0);

            queue.add(data);

            // ALGORITHM
            int ans = 10003;
            while (!queue.isEmpty()) {
                Data curr = queue.remove();

                if (curr.x == N - 1 && curr.y == N - 1) {
                    ans = Math.min(ans, total[curr.x][curr.y]);
                }

                if (map[curr.x][curr.y] >= ans)
                    continue;

                for (int i = 0; i < 4; i++) {
                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                        int newAmount = total[curr.x][curr.y] + map[nx][ny];

                        if (!visited[nx][ny] || total[nx][ny] > newAmount) {
                            visited[nx][ny] = true;
                            total[nx][ny] = newAmount;
                            queue.add(new Data(nx, ny));
                        }
                    }
                }
            }

            sb.append("#").append(testCase).append(" ").append(ans).append("\n");

        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        br.close();
    }

}
