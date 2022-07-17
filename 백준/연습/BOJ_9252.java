import java.io.*;

public class BOJ_9252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        char[] c1 = br.readLine().toCharArray();
        char[] c2 = br.readLine().toCharArray();
        int n = c1.length;
        int m = c2.length;
        int[][] dp = new int[n + 1][m + 1];
        int[][] route = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    route[i][j] = 1;
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        route[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        route[i][j] = 3;
                    }
                }
            }
        }

        int nx = n, ny = m;
        while (nx >= 1 && ny >= 1) {
            if (route[nx][ny] == 1) {
                sb.append(c1[nx - 1]);
                nx--;
                ny--;
            } else if (route[nx][ny] == 2) {
                nx--;
            } else if (route[nx][ny] == 3) {
                ny--;
            }
        }

        bw.write(dp[n][m] + "\n");
        bw.write(sb.reverse().toString());
        bw.flush();
        bw.close();

    }

}
