import java.io.*;

public class BOJ_2748 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];

        if (n <= 1) {
            sb.append(n);
        } else {

            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
