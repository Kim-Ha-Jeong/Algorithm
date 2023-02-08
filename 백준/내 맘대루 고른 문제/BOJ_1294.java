import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1294 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        PriorityQueue<String> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(br.readLine()+"~");
        }

        StringBuffer sb = new StringBuffer();

        while (!pq.isEmpty()) {
            String str = pq.poll();
            if (str.length() > 1) {
                sb.append(str.charAt(0));
                pq.add(str.substring(1));
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}