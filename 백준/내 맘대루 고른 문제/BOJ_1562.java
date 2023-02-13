import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1562 {
    static int mod = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[][][] dp = new long[n+1][11][1<<10];

        for(int i=1; i<10; i++){
            dp[1][i][1<<i] = 1;
        }

        for(int i=2; i<n+1; i++){
            for(int j=0; j<10; j++){
                for(int k=0; k<1024; k++){
                    int bit = k | (1 << j);
                    if(j == 0){
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % mod;
                    } else if(j == 9){
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % mod;
                    } else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k] + dp[i-1][j-1][k]) % mod;
                    }
                }
            }
        }

        long sum = 0;
        for(int i=0; i<10; i++){
            sum = (sum + dp[n][i][1023]) % mod;
        }

        bw.write(sum+"");
        bw.flush();
        bw.close();
    }
}
