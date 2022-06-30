import java.io.*;

public class BOJ_10844 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][10];
        int mod = 1000000000;

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % mod;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % mod;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        long ret = 0;
        for (int i = 0; i < 10; i++) {
            ret += dp[n][i];
        }

        sb.append(ret % mod);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
