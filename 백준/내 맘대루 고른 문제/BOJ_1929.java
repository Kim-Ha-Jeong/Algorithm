import java.io.*;
import java.util.*;

public class BOJ_1929 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i * i < n + 1; i++) {
            if (!prime[i])
                continue;
            for (int j = i * i; j < n + 1; j += i) {
                prime[j] = false;
            }
        }

        for (int i = m; i < n + 1; i++) {
            if (prime[i])
                sb.append(i).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
