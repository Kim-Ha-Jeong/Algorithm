import java.io.*;
public class DP_2 {
	static int p[],t[], dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		p = new int[n];
		t = new int[n];
		dp = new int[n+1];
	
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split(" ");
			t[i] = Integer.parseInt(s[0]);
			p[i] = Integer.parseInt(s[1]);
		}
		
		System.out.println(out(n));
	}
	
	static int out(int n) {
		int max = 0;
		for(int i=0; i<n; i++) {
			int day = i+t[i];
			if(day <= n) 
				dp[day] = Math.max(dp[i]+p[i], dp[day]);
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		return dp[n];
	}

}
