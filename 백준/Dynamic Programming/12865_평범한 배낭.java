import java.io.*;

public class DP_8 {
	static int w[],v[], dp[][], n, k;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s[] = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		k = Integer.parseInt(s[1]);
		
		w = new int[n+1];
		v = new int[n+1];
		dp = new int[n+1][k+1];
		
		for(int i=1; i<=n; i++) {
			s = br.readLine().split(" ");
			w[i] = Integer.parseInt(s[0]);
			v[i] = Integer.parseInt(s[1]);
		}
		
		System.out.println(knapsack());
	}
	
	static int knapsack() {
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<k+1; j++) {
				if(w[i] > j)
					dp[i][j] = dp[i-1][j];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
			}
		}
		
		return dp[n][k];
	}

}
