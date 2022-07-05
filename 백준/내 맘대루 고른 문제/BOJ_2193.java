import java.util.*;
import java.io.*;

public class BOJ_2193 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];

        if (n <= 2) {
            sb.append(1);
        } else {
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i < n + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}