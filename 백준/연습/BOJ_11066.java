import java.util.*;
import java.io.*;

public class BOJ_11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());
        int[] sum, arr;
        int[][] dp;

        while (tc-- > 0) {
            int k = Integer.parseInt(br.readLine());

            sum = new int[k + 1];
            arr = new int[k + 1];
            dp = new int[k + 1][k + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < k + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = (sum[i - 1] + arr[i]);
            }

            for (int i = 1; i < k + 1; i++) {
                for (int from = 1; from + i < k + 1; from++) {
                    int to = from + i;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int mid = from; mid < to; mid++) {
                        dp[from][to] = Math.min(dp[from][to],
                                dp[from][mid] + dp[mid + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            sb.append(dp[1][k]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
