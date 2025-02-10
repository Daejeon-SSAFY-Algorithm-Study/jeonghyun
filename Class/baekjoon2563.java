import java.util.*;
import java.io.*;

public class baekjoon2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> given = new ArrayList<Integer>();
            while (st.hasMoreTokens()) {
                given.add(Integer.parseInt(st.nextToken()));
            }

            int x = given.get(0);
            int y = given.get(1);

            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    map[j][k] = 1;
                }
            }
        }

        int total = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1) {
                    total++;
                }
            }
        }

        sb.append(total).append("\n");

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}
