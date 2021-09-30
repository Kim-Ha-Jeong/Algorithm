import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class BOJ_11057 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][10];
		
		for(int i=1; i<10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=1; i<n+1; i++) {
			dp[i][0] = 1;
		}
		
		for(int i=2; i<n+1; i++) {
			for(int j=1; j<10; j++) {
				for(int k=0; k<=j; k++) {
					dp[i][j] = (dp[i][j]+dp[i-1][k]) % 10007;
				}
			}
		}
		
		int ret = 0;
		for(int i=0; i<10; i++) {
			ret = (ret +dp[n][i]) % 10007;
		}
		
		bw.write(ret+"");
		bw.flush();
		bw.close();
	}

}
