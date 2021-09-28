import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1010 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		int[][] dp = new int[30][30];
		
		for(int i=0; i<30; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
		
		for(int i=2; i<30; i++) {
			for(int j=1; j<i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		while(tc-->0) {
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			
			sb.append(dp[m][n]).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
