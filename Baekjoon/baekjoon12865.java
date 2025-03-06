import java.io.*;
import java.util.*;

public class baekjoon12865 {
    static int N, K;
    static int[][] items;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new int[N + 2][2];
        map = new int[N + 2][K + 1];

        int w, v;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            items[i][0] = w;
            items[i][1] = v;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j - items[i][0] >= 0) {
                    map[i][j] = Math.max(map[i - 1][j], map[i - 1][j - items[i][0]] + items[i][1]);
                } else {
                    map[i][j] = map[i - 1][j];
                }
            }
        }

        sb.append(map[N][K]).append("\n");
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}
