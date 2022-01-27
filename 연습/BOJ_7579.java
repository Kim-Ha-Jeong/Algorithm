import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_7579 {
    static int n, m, totalCost = 0;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n + 1];
        int[] cost = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        int[][] dp = new int[n + 1][totalCost + 1];

        for (int i = cost[1]; i < totalCost + 1; i++) {
            dp[1][i] = memory[1];
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < totalCost + 1; j++) {
                if (j >= cost[i])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        for (int i = 1; i < totalCost + 1; i++) {
            if (dp[n][i] >= m) {
                ans = i;
                break;
            }
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
