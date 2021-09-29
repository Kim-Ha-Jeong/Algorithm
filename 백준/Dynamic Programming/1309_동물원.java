import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class BOJ_1309 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][3];
		
		for(int i=0; i<3; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<n+1; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		
		int ret = dp[n][0]+dp[n][1]+dp[n][2];
		
		bw.write((ret%9901)+"");
		bw.flush();
		bw.close();
	}

}
