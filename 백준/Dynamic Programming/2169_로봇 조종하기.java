import java.io.*;

public class DP_9 {
	static int map[][], dp[][], left[], right[];
	static int n, m;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		map = new int[n+1][m+1];
		dp = new int[n+1][m+1];
		left = new int[m+1];
		right = new int[m+1];
		
		for(int i=1; i<n+1; i++) {
			s = br.readLine().split(" ");
			for(int j=1; j<m+1; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		
		for(int i=1; i<m+1; i++)
			dp[1][i] = (dp[1][i-1] + map[1][i]);
		
		System.out.println(control());
	}
	
	static int control() {
		
		for(int r=2; r<n+1; r++) {
			left[1] = dp[r-1][1] + map[r][1];
			for(int c=2; c<m+1; c++)
				left[c] = Math.max(left[c-1], dp[r-1][c]) + map[r][c];
			
			right[m] = dp[r-1][m] + map[r][m];
			for(int c=m-1; c>=1; c--)
				right[c] = Math.max(right[c+1], dp[r-1][c]) + map[r][c];
			
			for(int c=1; c<m+1; c++)
				dp[r][c] = Math.max(left[c], right[c]);
			
		}
		
		return dp[n][m];
	}
}
