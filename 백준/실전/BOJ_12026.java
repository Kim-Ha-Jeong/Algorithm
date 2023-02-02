import java.util.*;
import java.io.*;

public class BOJ_12026 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        char[] c = new char[n];
        c = br.readLine().toCharArray();

        int[] dp = new int[n];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        ArrayList<Character> key = new ArrayList<>();
        key.add('B');
        key.add('O');
        key.add('J');

        for (int i = 1; i < n; i++) {
            int idx = key.indexOf(c[i]);
            char target = idx == 0 ? key.get(2) : key.get(idx - 1);
            for (int j = i - 1; j >= 0; j--) {
                if (c[j] == target && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + (i - j) * (i - j));
                }
            }
        }

        int ans = dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

}
