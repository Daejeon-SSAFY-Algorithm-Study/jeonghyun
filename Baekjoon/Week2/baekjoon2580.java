import java.io.*;
import java.util.*;

public class baekjoon2580 {
    private static int[][] map;

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
            System.out.print(sb);
        }
    }

    private static boolean validate() {
        // Check each row and column
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (map[i][j] != 0 && rowCheck[map[i][j]])
                    return false;
                if (map[j][i] != 0 && colCheck[map[j][i]])
                    return false;
                rowCheck[map[i][j]] = true;
                colCheck[map[j][i]] = true;
            }
        }

        // Check 3x3 boxes
        for (int block = 0; block < 9; block++) {
            boolean[] check = new boolean[10];
            int rowStart = (block / 3) * 3;
            int colStart = (block % 3) * 3;
            for (int i = rowStart; i < rowStart + 3; i++) {
                for (int j = colStart; j < colStart + 3; j++) {
                    if (map[i][j] != 0 && check[map[i][j]])
                        return false;
                    check[map[i][j]] = true;
                }
            }
        }
        return true;
    }

    private static void solve(int x, int y) {
        // Validate
        if (x == 9) {
            if (validate()) {
                printResult();
                System.exit(0);
            }
            return;
        }

        // End of row
        if (y == 9) {
            solve(x + 1, 0);
            return;
        }

        // Designated coordinate is filled
        if (map[x][y] != 0) {
            solve(x, y + 1);
            return;
        }

        List<Integer> leftover = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 9; i++) {
            if (leftover.contains(map[x][i])) {
                leftover.remove(leftover.indexOf(map[x][i]));
            }
        }

        for (int i = 0; i < leftover.size(); i++) {
            map[x][y] = leftover.get(i);
            solve(x, y + 1);
        }
        map[x][y] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0);

    }

}
