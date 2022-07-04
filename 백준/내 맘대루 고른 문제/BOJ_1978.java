import java.util.*;
import java.io.*;

public class BOJ_1978 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        boolean[] prime = new boolean[1001];

        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i * i < 1001; i++) {
            if (!prime[i])
                continue;
            for (int j = i; j * i < 1001; j++) {
                prime[i * j] = false;
            }
        }

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (prime[x])
                cnt++;
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
