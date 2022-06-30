import java.io.*;

public class BOJ_11057 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int mod = 10007;
        int[][] dp = new int[n + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[n][i]) % mod;
        }

        sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
