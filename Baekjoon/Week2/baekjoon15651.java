package Week2;

import java.io.*;
import java.util.*;

public class baekjoon15651 {
    private static void solution(int N, int M, int depth, String str, BufferedWriter bw) throws IOException {
        if (depth == M) {
            bw.write(str + "\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            solution(N, M, depth + 1, str + i + " ", bw);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        solution(N, M, 0, "", bw);

        br.close();
        bw.flush();
        bw.close();
    }
}
