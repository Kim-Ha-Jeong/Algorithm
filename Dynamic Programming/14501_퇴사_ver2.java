package study;
import java.util.*;
public class Leave2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int max = 0;
		int n = sc.nextInt();
		int t[] = new int[n+5];
		int p[] = new int[n+5];
		int dp[] = new int[n+5];
		
		for(int i=1; i<=n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
		}
		
		for(int i=n; i>=1; i--) {
			if(i+t[i] <= n+1)
				dp[i] = Math.max(dp[i+1], dp[i+t[i]]+p[i]);
				
			max = Math.max(dp[i], max);
		}
		
		System.out.println(max);
	}

}
