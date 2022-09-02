import java.io.*;

public class BOJ_11058 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i > 6) {
                for (int j = 3; j < 6; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
                }
            }
        }

        bw.write(sb.append(dp[n]).toString());
        bw.flush();
        bw.close();
    }
}
