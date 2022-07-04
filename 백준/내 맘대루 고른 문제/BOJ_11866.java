import java.io.*;
import java.util.*;

public class BOJ_11866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] remove = new boolean[n + 1];

        int idx = 0;
        int index = 0;
        int removed = 1;

        sb.append("<");

        while (true) {
            if (removed == n + 1)
                break;

            if (index == k) {
                remove[idx] = true;
                index = 0;
                removed++;
                if (idx == 0) {
                    sb.append(n);
                } else {
                    sb.append(idx);
                }
                if (removed != n + 1) {
                    sb.append(", ");
                }
            }

            idx = (idx + 1) % n;

            if (remove[idx] == true)
                continue;
            index++;
        }

        sb.append(">");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
