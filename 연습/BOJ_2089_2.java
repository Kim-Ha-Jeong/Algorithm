import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_2089_2 {
    static int n;
    static int[][] dp;
    static int[][] w;
    static int INF = 11000000;
    static int visitAll;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visitAll = (1 << n) - 1;
        w = new int[n][n];
        dp = new int[n][visitAll];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        sb.append(dfs(0, 1));

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int dfs(int city, int visit) {
        if (visit == visitAll) {
            if (w[city][0] == 0)
                return INF;
            return w[city][0];
        }

        if (dp[city][visit] != INF)
            return dp[city][visit];

        for (int i = 0; i < n; i++) {
            int next = (1 << i);
            int nextVisit = visit | next;

            if (nextVisit == visit)
                continue;
            if (w[city][i] == 0)
                continue;

            dp[city][visit] = Math.min(dp[city][visit], dfs(i, nextVisit) + w[city][i]);
        }

        return dp[city][visit];
    }

}
