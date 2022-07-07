import java.io.*;
import java.util.*;

public class BOJ_16194 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] p = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            dp[i] = p[i];
            for (int j = 1; j < i / 2 + 1; j++) {
                dp[i] = Math.min(dp[i - j] + dp[j], dp[i]);
            }
        }

        bw.write(sb.append(dp[n]).toString());
        bw.flush();
        bw.close();
    }

}
