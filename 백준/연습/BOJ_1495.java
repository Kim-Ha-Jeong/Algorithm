import java.util.*;
import java.io.*;

public class BOJ_1495 {
    static int n, s, m;
    static int[] diff;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        diff = new int[n + 1];
        dp = new boolean[n + 1][1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            diff[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][s] = true;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 1001; j++) {
                if (dp[i - 1][j]) {
                    if (j + diff[i] <= m) {
                        dp[i][j + diff[i]] = true;
                    }
                    if (j - diff[i] >= 0) {
                        dp[i][j - diff[i]] = true;
                    }
                }
            }
        }

        int ans = -1;
        for (int i = 1000; i >= 0; i--) {
            if (dp[n][i]) {
                ans = i;
                break;
            }
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
