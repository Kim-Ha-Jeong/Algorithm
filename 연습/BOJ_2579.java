import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][2];
        int[] arr = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1][0] = arr[1];
        for (int i = 2; i < n + 1; i++) {
            if (i == 2)
                dp[i][0] = dp[i - 1][0] + arr[i];
            else
                dp[i][0] = dp[i - 1][1] + arr[i];
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i];
        }

        sb.append(Math.max(dp[n][0], dp[n][1]));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
