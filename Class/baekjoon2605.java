import java.io.*;
import java.util.*;

public class baekjoon2605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        ArrayList<Integer> line = new ArrayList<>();
        
        for(int i = 1; i <= N; i++) {
            int back = Integer.parseInt(st.nextToken());
            line.add(line.size() - back, i);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < line.size(); i++) {
            sb.append(line.get(i)).append(" ");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}

