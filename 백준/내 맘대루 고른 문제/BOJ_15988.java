import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_15988 {
    static final int MAX = 1000000;
    static final int DIVIDER = 1000000009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[MAX + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        while (n-- > 0) {
            int m = Integer.parseInt(br.readLine());

            if (m <= 3)
                sb.append(dp[m]).append("\n");
            else {
                for (int i = 4; i <= m; i++) {
                    dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % DIVIDER;
                }
                sb.append(dp[m]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
