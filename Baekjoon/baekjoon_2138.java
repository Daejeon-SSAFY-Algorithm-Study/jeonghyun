import java.io.*;
import java.util.*;

public class baekjoon_2138 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		
		int[] current = new int[n];
		int[] desired = new int[n];

		String line = br.readLine();

		for(int i = 0 ; i < n; i++) {
			current[i] = line.charAt(i) - '0';
		}
		

		line = br.readLine();
		for(int i = 0; i < n ; i++) {
			desired[i] = line.charAt(i) - '0';
		}


		// Algorithm
		int answer = solution(n, current, desired);

		StringBuilder sb = new StringBuilder();

		sb.append(answer).append("\n");

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();
	}

	public static int solution(int n, int[] current, int[] desired) {
		int[] case1 = current.clone();
        int[] case2 = current.clone();

        int count1 = tryCase(n, case1, desired, false);
        int count2 = tryCase(n, case2, desired, true);

        if(count1 == -1 && count2 == -1) return -1;
        if(count1 == -1) return count2;
        if(count2 == -1) return count1;

        return Math.min(count1, count2);

        
	}

    private static int tryCase(int n , int[] current, int[] desired, boolean pressFirst) {
        int count = 0;

        if(pressFirst) {
            current[0] = Math.abs(current[0] - 1);
            current[1] = Math.abs(current[1] - 1);
            count = 1;
		}

        for(int i =  1; i < n - 1; i++) {
            if(current[i-1] != desired[i-1]) {
                current[i-1] = Math.abs(current[i-1] -1);
                current[i] = Math.abs(current[i] - 1);
                current[i+1] = Math.abs(current[i+1] - 1);

                count++;
            }
        }

        if(n > 1 && current[n-2] != desired[n-2]) {
            current[n-2] = Math.abs(current[n-2] - 1);
            current[n-1] = Math.abs(current[n-1] - 1);
            count++;
        }
        
        return Arrays.equals(current, desired) ? count : -1;
    }
}

