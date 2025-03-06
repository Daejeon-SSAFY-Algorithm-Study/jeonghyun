import java.io.*;
import java.util.*;

public class baekjoon2098 {
    static int N;

    static int[][] input;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        input = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int val = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}