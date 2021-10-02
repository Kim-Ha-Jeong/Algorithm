import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_10835 {
	static int[][] dp;
	static int n;
	static int[] l;
	static int[] r;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n][n];
		l = new int[n];
		r = new int[n];
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			l[i] = Integer.parseInt(s[i]);
		}
		
		s = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			r[i] = Integer.parseInt(s[i]);
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				dp[i][j] = -1;
			}
		}
		
		bw.write(solve(0,0)+"");
		bw.flush();
		bw.close();
		
	}
	
	static int solve(int left, int right) {
		if(left >= n || right >= n) return 0;
		
		if(dp[left][right] != -1) return dp[left][right];
		
		dp[left][right] = Math.max(solve(left+1, right), solve(left+1,right+1));
		
		if(l[left] > r[right])
			dp[left][right] = Math.max(dp[left][right], solve(left, right+1)+r[right]);
		
		return dp[left][right];
	}

}
