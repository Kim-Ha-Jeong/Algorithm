import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[3][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (i == 0) {
                dp[0][0] = dp[0][1] = x;
                dp[1][0] = dp[1][1] = y;
                dp[2][0] = dp[2][1] = z;
            } else {
                int max0 = dp[0][0], max2 = dp[2][0];
                dp[0][0] = Math.max(dp[0][0], dp[1][0]) + x;
                dp[2][0] = Math.max(dp[2][0], dp[1][0]) + z;
                dp[1][0] = Math.max(dp[1][0], Math.max(max0, max2)) + y;

                int min0 = dp[0][1], min2 = dp[2][1];
                dp[0][1] = Math.min(dp[0][1], dp[1][1]) + x;
                dp[2][1] = Math.min(dp[2][1], dp[1][1]) + z;
                dp[1][1] = Math.min(dp[1][1], Math.min(min0, min2)) + y;
            }
        }

        sb.append(Math.max(dp[0][0], Math.max(dp[1][0], dp[2][0]))).append(" ")
                .append(Math.min(dp[0][1], Math.min(dp[1][1], dp[2][1])));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
