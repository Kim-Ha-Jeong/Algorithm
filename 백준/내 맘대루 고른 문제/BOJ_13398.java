import java.io.*;
import java.util.*;

public class BOJ_13398 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] dp2 = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 1], arr[i]);
            max = Math.max(dp[i], max);
        }

        dp2[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1] + arr[i], arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int tmp = dp[i - 1] + dp2[i + 1];
            max = Math.max(tmp, max);
        }

        bw.write(sb.append(max).toString());
        bw.flush();
        bw.close();

    }
}
