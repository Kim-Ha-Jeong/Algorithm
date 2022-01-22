import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1256_2 {
    static int n, m, k, sum;
    static int[][] dp;
    static int MAX = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        sum = n + m;

        dp = new int[sum + 1][sum + 1];

        if (combination(sum, n) < k)
            sb.append(-1);
        else
            query(n, m, k, sb);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void query(int n, int m, int k, StringBuffer sb) {
        if (n == 0 && m == 0) {
            return;
        } else if (n == 0) {
            sb.append("z");
            query(n, m - 1, k, sb);
        } else if (m == 0) {
            sb.append("a");
            query(n - 1, m, k, sb);
        } else {
            int leftValue = combination(n + m - 1, n - 1);
            if (leftValue < k) {
                sb.append("z");
                query(n, m - 1, k - leftValue, sb);
            } else {
                sb.append("a");
                query(n - 1, m, k, sb);
            }
        }
    }

    static int combination(int n, int r) {
        if (r == 0 || n == r)
            return 1;
        else if (dp[n][r] != 0)
            return dp[n][r];
        else
            return dp[n][r] = Math.min(MAX, combination(n - 1, r) + combination(n - 1, r - 1));
    }

}
