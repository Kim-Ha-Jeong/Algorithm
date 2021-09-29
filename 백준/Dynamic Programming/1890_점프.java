import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class BOJ_1890 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		long[][] dp = new long[n][n];
		dp[0][0] = 1;
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i == n-1 && j == n-1) break;
				
				if(dp[i][j] != 0) {
					int val = map[i][j];
					if(val + j < n) {
						dp[i][val+j] += dp[i][j];
					}
					
					if(map[i][j] + i < n) {
						dp[val+i][j] += dp[i][j];
					}
				}
			}
		}
		
		bw.write(dp[n-1][n-1]+"");
		bw.flush();
		bw.close();
	}
}
