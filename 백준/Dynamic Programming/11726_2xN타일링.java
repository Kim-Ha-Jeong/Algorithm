import java.util.Scanner;

public class DP_1 {
	static int dp[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		dp = new int[n+1];
		System.out.println(tiling(n));

	}
	
	static int tiling(int n) {
		if(n == 0 || n == 1) return 1;
		
		if(dp[n] > 0) return dp[n];
		
		dp[n] = tiling(n-1) + tiling(n-2);
		dp[n] %= 100007;
		
		return dp[n];
		
	}

}
