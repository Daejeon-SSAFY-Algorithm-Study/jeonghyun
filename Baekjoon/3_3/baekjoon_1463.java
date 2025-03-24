import java.io.*;

public class baekjoon_1463 {
    static int N;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.MAX_VALUE;
        }

        for (int i = N; i > 1; i--) {
            if (i % 3 == 0) {
                map[i / 3] = Math.min(map[i] + 1, map[i / 3]);
            }
            if (i % 2 == 0) {
                map[i / 2] = Math.min(map[i] + 1, map[i / 2]);
            }
            map[i - 1] = Math.min(map[i] + 1, map[i - 1]);
        }
        System.out.println(map[1]);

        br.close();
    }

}
