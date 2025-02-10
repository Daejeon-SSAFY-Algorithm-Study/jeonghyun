import java.io.*;
import java.util.*;

public class baekjoon2491 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] upCounter = new int[N];
        int[] downCounter = new int[N];

        upCounter[0] = 1;
        downCounter[0] = 1;
        for (int i = 0; i < N - 1; i++) {
            if (arr[i + 1] >= arr[i]) {
                upCounter[i + 1] = upCounter[i] + 1;
            } else {
                upCounter[i + 1] = 1;
            }

            if (arr[i + 1] <= arr[i]) {
                downCounter[i + 1] = downCounter[i] + 1;
            } else {
                downCounter[i + 1] = 1;
            }
        }

        Arrays.sort(upCounter);
        Arrays.sort(downCounter);
        int ans = Math.max(upCounter[upCounter.length - 1], downCounter[downCounter.length - 1]);
        sb.append(ans).append("\n");

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
