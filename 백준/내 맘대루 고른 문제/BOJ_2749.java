import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2749 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        int mod = 1000000;
        int repeat = 15 * 100000;


        int[] dp = new int[repeat+1];
        dp[1] = 1;

        for(int i=2; i<repeat+1; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % mod;
        }
        // 15 * 10^5 주기를 가짐

        int now = (int)(n % repeat);
        bw.write(dp[now]+"");
        bw.flush();
        bw.close();
    }
}
