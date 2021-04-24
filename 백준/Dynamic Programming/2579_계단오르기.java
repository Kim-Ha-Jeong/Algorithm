import java.io.*;
import java.util.*;
public class DP_4 {
	static int n;
	static int score[], dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		score = new int[n+1];
		dp = new int[n+1];
		
		for(int i=1; i<=n; i++) 
			score[i] = Integer.parseInt(br.readLine());
		
		dp[1] = score[1];
		if(n>=2) dp[2] = dp[1]+score[2];
		
		for(int i=3; i<=n; i++) {
			dp[i] = Math.max(dp[i-3]+score[i]+score[i-1], dp[i-2]+score[i]);
		}
		

		System.out.println(dp[n]);
		
	}

}
