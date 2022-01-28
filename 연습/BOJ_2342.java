import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class BOJ_2342 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String[] s = br.readLine().split(" ");

        int n = s.length - 1;
        int INF = 100001;

        int[] arr = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(s[i - 1]);
        }

        int[][][] dp = new int[n + 1][5][5];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[1][0][arr[1]] = 2;
        dp[0][arr[1]][0] = 2;

        int next;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (dp[i][j][k] != INF) {
                        next = arr[i + 1];
                        if (k != next) {
                            dp[i + 1][next][k] = Math.min(dp[i + 1][next][k], dp[i][j][k] + getScore(j, next));
                        }

                        if (j != next) {
                            dp[i + 1][j][next] = Math.min(dp[i + 1][j][next], dp[i][j][k] + getScore(k, next));
                        }
                    }
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(dp[n][i][j], ans);
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int getScore(int now, int next) {
        if (now == 0)
            return 2;
        else if (Math.abs(now - next) == 2)
            return 4;
        else if (now == next)
            return 1;
        else
            return 3;
    }

}
