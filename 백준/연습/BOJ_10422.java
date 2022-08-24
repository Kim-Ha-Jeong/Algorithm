import java.io.*;

public class BOJ_10422 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];

        int max = -1;
        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long[] dp = new long[max + 1];

        long mod = 1000000007;
        dp[0] = 1;

        for (int i = 2; i < max + 1; i++) {
            if (i % 2 == 1)
                continue;
            for (int j = 0; j < i - 1; j += 2) {
                dp[i] += (dp[j] * dp[i - j - 2]);
                dp[i] %= mod;
            }
        }

        for (int i = 0; i < t; i++) {
            sb.append(dp[arr[i]]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
