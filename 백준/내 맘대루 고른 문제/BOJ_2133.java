import java.io.*;

public class BOJ_2133 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];

        if (n == 0 || n % 2 == 1) {
            sb.append(0);
        } else {
            dp[0] = 1;
            dp[2] = 3;
            for (int i = 4; i < n + 1; i += 2) {
                dp[i] = dp[2] * dp[i - 2];
                for (int j = 0; j <= i - 4; j += 2) {
                    dp[i] += dp[j] * 2;
                }
            }
            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
