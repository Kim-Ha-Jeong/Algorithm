import java.io.*;
import java.util.*;

public class BOJ_5557 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n][21];
        dp[0][arr[0]] = 1;

        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i - 1][j] == 0)
                    continue;
                if (j + arr[i] <= 20) {
                    dp[i][j + arr[i]] += dp[i - 1][j];
                }
                if (j - arr[i] >= 0) {
                    dp[i][j - arr[i]] += dp[i - 1][j];
                }
            }
        }

        bw.write(sb.append(dp[n - 2][arr[n - 1]]).toString());
        bw.flush();
        bw.close();
    }

}
