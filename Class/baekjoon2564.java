import java.io.*;
import java.util.*;

public class baekjoon2564 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int circumference = 2 * (x + y);

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        // 1
        // 3 4
        // 2
        ArrayList<Integer> storeIdx = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            int coordinate_count = 0;

            if (direction == 2) {
                coordinate_count = coordinate_count + y + x;
                coordinate_count = coordinate_count + (x - amount);
            } else if (direction == 3) {
                coordinate_count = coordinate_count + x * 2 + y;
                coordinate_count = coordinate_count + (y - amount);
            } else if (direction == 4) {
                coordinate_count = coordinate_count + x;
                coordinate_count += amount;
            } else {
                coordinate_count += amount;
            }
            storeIdx.add(coordinate_count);
        }

        // INPUT FOR SECURITY
        st = new StringTokenizer(br.readLine());
        int direction = Integer.parseInt(st.nextToken());
        int amount = Integer.parseInt(st.nextToken());

        int coordinate_count = 0;
        if (direction == 2) {
            coordinate_count = coordinate_count + y + x;
            coordinate_count = coordinate_count + (x - amount);
        } else if (direction == 3) {
            coordinate_count = coordinate_count + x * 2 + y;
            coordinate_count = coordinate_count + (y - amount);
        } else if (direction == 4) {
            coordinate_count = coordinate_count + x;
            coordinate_count += amount;
        } else {
            coordinate_count += amount;
        }
        int securityIdx = coordinate_count;

        // Algorithm
        int total = 0;
        for (int i = 0; i < N; i++) {
            int storeCoor = storeIdx.get(i);
            int distance = Math.abs(storeCoor - securityIdx);
            int opposite = circumference - distance;

            total += Math.min(distance, opposite);

        }
        sb.append(total).append("\n");

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }
}
