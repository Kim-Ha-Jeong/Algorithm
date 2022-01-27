import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_2098 {
    static int[][] w;
    static int n;
    static int[][] dp;
    static int INF = 11000000;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        w = new int[n][n];
        dp = new int[n][(1 << n)];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = INF;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][1] = 0;
        dfs(0, 1);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void dfs(int city, int visit) {
        if (visit == (1 << n) - 1) {
            if (w[city][0] == 0)
                return;

            ans = Math.min(ans, dp[city][visit] + w[city][0]);
            return;
        }

        for (int i = 0; i < n; i++) {
            int next = (1 << i);
            int nextVisit = visit | next;
            if (nextVisit == visit)
                continue;
            if (w[city][i] == 0)
                continue;

            if (dp[i][nextVisit] > dp[city][visit] + w[city][i]) {
                dp[i][nextVisit] = dp[city][visit] + w[city][i];
                dfs(i, nextVisit);
            }
        }
    }
}
