import java.util.*;
import java.io.*;


// 메모리 : 14524 KB
// 시간 : 112 ms
class baekjoon2309 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        LinkedList<Integer> heights = new LinkedList<Integer>();
        for(int i = 0; i < 9 ; i++) {
            heights.add(Integer.parseInt(br.readLine()));
        }

        int[] list = new int[7];

        StringBuilder sb = new StringBuilder();

        Queue<LinkedList<Integer>> queue = new LinkedList<>();
        queue.add(heights);

        while(!queue.isEmpty()) {
            LinkedList<Integer> current = queue.remove();
            boolean found = false;

            if(current.size() > 7) {
                for(int i = 0; i < current.size(); i++) {
                    LinkedList<Integer> next = new LinkedList<>(current);
                    int ele = next.get(i);
                    next.remove(i);
                    int sum = next.stream().mapToInt(Integer::intValue).sum();
                    
                    if (sum == 100 && next.size() == 7) {
                        list = next.stream().mapToInt(Integer::intValue).toArray();
                        found = true;
                        break;
                    } else {
                        queue.add(next);
                    }
                }
            }

            if(found) break;
        }

        Arrays.sort(list);

        for(int i = 0 ; i < list.length; i++) {
            sb.append(list[i]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }
}
