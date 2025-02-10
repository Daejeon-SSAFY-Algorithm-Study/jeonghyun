import java.util.*;
import java.io.*;

public class baekjoon2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] lengths = new int[6];
        int[] directions = new int[6];

        int maxW = 0;
        int maxH = 0;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            directions[i] = dir;
            lengths[i] = length;

            if (dir == 1 || dir == 2) {
                maxW = Math.max(maxW, length);
            }
            if (dir == 3 || dir == 4) {
                maxH = Math.max(maxH, length);
            }
        }

        int ans = maxW * maxH * N;
        int lengthOne = 0;
        int lengthTwo = 0;
        for (int i = 0; i < 6; i++) {
            if (directions[i] == directions[(i + 2) % 6] && directions[(i + 1) % 6] == directions[(i + 3) % 6]) {
                lengthOne = lengths[(i + 1) % 6];
                lengthTwo = lengths[(i + 2) % 6];
                break;
            }
        }

        int sub_area = N * lengthOne * lengthTwo;

        ans -= sub_area;

        sb.append(ans).append("\n");
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}
