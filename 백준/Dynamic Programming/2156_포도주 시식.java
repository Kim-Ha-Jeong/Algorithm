import java.util.Scanner;

public class DP_3 {
	static int p[];
	static int dp[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		p = new int[n+10];
		dp = new int[n+10];
		
		for(int i=1; i<=n; i++)
			p[i] = sc.nextInt();
		
		dp[1] = p[1];
		dp[2] = p[1]+p[2];
		
		System.out.println(maxWine(n));

	}
	
	static int maxWine(int n) {
		for(int i=3; i<=n; i++) {
			int ret = 0;
			ret = Math.max(p[i]+dp[i-2], dp[i-1]);
			ret = Math.max(ret, dp[i-3]+p[i-1]+p[i]);
			dp[i] = ret;
		}
		
		return dp[n];
	}

}
