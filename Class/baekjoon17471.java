import java.io.*;
import java.util.*;

public class baekjoon17471 {
    static int N;
    static int[] people;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static boolean[] selected;

    private static boolean isPossible(ArrayList<Integer> team) {
        if (team.size() == 0)
            return false;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.add(team.get(0));
        visited[team.get(0)] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int i = 1; i <= N; i++) {
                if (map[current][i] == 1 && !visited[i] && team.contains(i)) {
                    q.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }

        return count == team.size();
    }

    private static void solution(int idx) {
        if (idx > N) {
            ArrayList<Integer> team1 = new ArrayList<>();
            ArrayList<Integer> team2 = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (selected[i])
                    team1.add(i);
                else
                    team2.add(i);
            }

            // CHECK IF IT IS POSSIBLE
            if (isPossible(team1) && isPossible(team2)) {
                int sum1 = 0, sum2 = 0;
                for (int num : team1)
                    sum1 += people[num];
                for (int num : team2)
                    sum2 += people[num];
                ans = Math.min(ans, Math.abs(sum1 - sum2));
            }
            return;
        }
        selected[idx] = true;
        solution(idx + 1);
        selected[idx] = false;
        solution(idx + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); // Changed from int N = ... to N = ...

        selected = new boolean[N + 1];
        people = new int[N + 1];
        map = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                int idx = Integer.parseInt(st.nextToken());
                map[i][idx] = 1;
            }
        }

        solution(1);

        if (ans == Integer.MAX_VALUE)
            ans = -1;
        sb.append(ans).append("\n");

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }

}
