import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
public class BOJ_2225 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		int[][] dp = new int[n+1][k+1];
		
		for(int i=1; i<n+1; i++) {
			dp[i][1] = 1;
		}
		
		for(int i=1; i<k+1; i++) {
			dp[1][i] = i;
		}
		
		for(int i=2; i<n+1; i++) {
			for(int j=2; j<k+1; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
			}
		}
		
		bw.write(dp[n][k]+"");
		bw.flush();
		bw.close();
	}

}
