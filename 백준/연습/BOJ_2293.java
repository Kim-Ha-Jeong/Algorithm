import java.io.*;
import java.util.*;

public class BOJ_2293 {
    static int n, k;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j >= arr[i]) {
                    dp[j] += dp[j - arr[i]];
                }
            }
        }

        bw.write(sb.append(dp[k]).toString());
        bw.flush();
        bw.close();
    }

}
