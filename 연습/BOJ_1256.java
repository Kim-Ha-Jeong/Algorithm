import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1256 { // 다시 풀기
    static int MAX = 1000000000;
    static int[][] dp;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int sum = n + m;

        dp = new int[sum + 1][sum + 1];

        query(sum - 1, n, k, sum);

        setTriangle();
    }

    static void query(int i, int j, int k, int sum) {
        if (dp[i][j] < k) {
            query(i, sum - j, k - dp[i][j], sum);
        } else {
            query(i - 1, j, k, sum);
        }

    }

    static void setTriangle() {
        Arrays.fill(dp[1], 1);

        for (int i = 2; i < n + m + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                if (j == 1 || i == j)
                    dp[i][j] = 1;
                else {
                    if (dp[i - 1][j] + dp[i - 1][j - 1] > 1e9) {
                        dp[i][j] = (int) 1e9;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                    }
                }
            }
        }
    }

}
