import java.util.*;
import java.io.*;

public class BOJ_2294 {
    static int[] dp, arr;
    static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[k + 1];
        arr = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = arr[i]; j < k + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }

        int ans = -1;
        if (dp[k] != Integer.MAX_VALUE - 1) {
            ans = dp[k];
        }

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();

    }
}
