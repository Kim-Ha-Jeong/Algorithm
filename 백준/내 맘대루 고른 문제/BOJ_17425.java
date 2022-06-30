import java.util.*;
import java.io.*;

public class BOJ_17425 {
    static int MAX = 1000001;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        long[] dp = new long[MAX];
        long[] sum = new long[MAX];

        Arrays.fill(dp, 1);

        for (int i = 2; i < MAX; i++) {
            for (int j = 1; i * j < MAX; j++) {
                dp[i * j] += i;
            }
        }

        for (int i = 1; i < MAX; i++) {
            sum[i] = sum[i - 1] + dp[i];
        }

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            sb.append(sum[n]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
