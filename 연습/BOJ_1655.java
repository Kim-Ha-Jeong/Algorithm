import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Collections;

public class BOJ_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());

            if (minHeap.size() == maxHeap.size()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() < minHeap.peek()) {
                    int tmp = maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(tmp);
                }
            }

            sb.append(minHeap.peek()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
