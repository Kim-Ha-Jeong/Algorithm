import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][n + 1];
        int[][] arr = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (n == 1) {
            System.out.println(arr[1][1]);
            return;
        }

        dp[1][1] = arr[1][1];
        dp[2][1] = dp[1][1] + arr[2][1];
        dp[2][2] = dp[1][1] + arr[2][2];

        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
            }
        }

        int max = -1;
        for (int i = 1; i < n + 1; i++) {
            max = Math.max(max, dp[n][i]);
        }

        sb.append(max);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
