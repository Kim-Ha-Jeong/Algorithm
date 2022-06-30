import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11726 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int mod = 10007;

        if (n <= 2) {
            sb.append(n);
        } else {
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i < n + 1; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
            }
            sb.append(dp[n]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
