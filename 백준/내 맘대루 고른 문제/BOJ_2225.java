import java.util.*;
import java.io.*;

public class BOJ_2225 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = 1;
        }

        for (int i = 0; i < k + 1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 2; j < k + 1; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
            }
        }

        sb.append(dp[n][k]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
