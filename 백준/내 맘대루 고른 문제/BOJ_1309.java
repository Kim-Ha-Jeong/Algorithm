import java.io.*;

public class BOJ_1309 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int mod = 9901;

        if (n == 1) {
            sb.append(3);
        } else {
            dp[0] = 1;
            dp[1] = 3;

            for (int i = 2; i < n + 1; i++) {
                dp[i] = (2 * dp[i - 1] + dp[i - 2]) % mod;
            }

            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
