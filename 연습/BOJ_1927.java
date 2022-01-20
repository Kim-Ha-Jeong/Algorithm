import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_1927 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            int tmp = Integer.parseInt(br.readLine());

            if (tmp == 0) {
                if (pq.size() == 0)
                    sb.append(0);
                else
                    sb.append(pq.poll());
                sb.append("\n");
            } else {
                pq.add(tmp);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
