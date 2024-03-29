import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1256 {
    static int MAX = 1000000000;
    static int[][] dp;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int sum = n + m;

        dp = new int[sum + 1][sum + 1];

        if (setTriangle(n + m, m) < k) {
            sb.append(-1);
        } else {
            query(n, m, k, sb);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void query(int n /* a개수 */, int m /* z개수 */, int k, StringBuffer sb) {
        if (n + m == 0) {
            return; // 다 골랐음
        } else if (n == 0) {
            sb.append("z");
            query(n, m - 1, k, sb);
        } else if (m == 0) {
            sb.append("a");
            query(n - 1, m, k, sb);
        } else {
            int leftCount = setTriangle(n + m - 1, n - 1); // n+m은 이전의 전체 자리 수, 거기서 한 자리를 쓰기 위해서 -1을 함
            // leftCount이기 때문에 앞 한자리에는 a가 있음 그렇기에 a의 개수는 n이 아닌 n-1이 됨
            if (leftCount >= k) {
                sb.append("a");
                query(n - 1, m, k, sb);
            } else {
                sb.append("z");
                query(n, m - 1, k - leftCount, sb);
            }
        }

    }

    static int setTriangle(int n, int r) {
        if (n == r || r == 0)
            return 1;
        else if (dp[n][r] != 0)
            return dp[n][r];
        else
            return dp[n][r] = Math.min((int) 1e9, setTriangle(n - 1, r - 1) + setTriangle(n - 1, r));
    }

}
