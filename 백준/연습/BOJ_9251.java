import java.io.*;

public class BOJ_9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        char[] c1 = br.readLine().toCharArray();
        char[] c2 = br.readLine().toCharArray();
        int n = c1.length;
        int m = c2.length;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        bw.write(sb.append(dp[n][m]).toString());
        bw.flush();
        bw.close();

    }

}
