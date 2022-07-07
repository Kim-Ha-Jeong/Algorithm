import java.util.*;
import java.io.*;

public class BOJ_1149 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int[][] price = new int[n + 1][3];
        int[][] dp = new int[n + 1][3];

        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][0] = price[1][0];
        dp[1][1] = price[1][1];
        dp[1][2] = price[1][2];

        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + price[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + price[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + price[i][2];
        }

        int ans = Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
