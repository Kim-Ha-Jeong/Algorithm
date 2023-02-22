import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1947 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n+1];
        if(n > 1) {
            dp[2] = 1;

            long mod = 1000000000;

            for (int i = 3; i < n + 1; i++) {
                dp[i] = ((i - 1) * (dp[i - 1] + dp[i - 2])) % mod;
            }
        }

        bw.write(dp[n]+"");
        bw.flush();
        bw.close();
    }
}
