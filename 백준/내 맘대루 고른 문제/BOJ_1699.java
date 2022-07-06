import java.io.*;
import java.util.*;

public class BOJ_1699 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        if (n == 1) {
            sb.append(1);
        } else {
            dp[1] = 1;
            dp[2] = 2;
            int idx = 2;
            for (int i = 3; i < n + 1; i++) {
                if (i == idx * idx) {
                    dp[i] = 1;
                    idx++;
                    continue;
                }
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
                }
            }

            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
