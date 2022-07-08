import java.io.*;

public class BOJ_11727 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int mod = 10007;

        if (n == 1) {
            sb.append(1);
        } else {
            dp[0] = dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % mod;
            }
            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
