import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_10 {
	static int arr[], n, dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		
		String s[] = br.readLine().split(" ");
		
		for(int i=0; i<n; i++) 
			arr[i] = Integer.parseInt(s[i]);
		
		
		Arrays.fill(dp, 1);
		dp[1] = arr[0] >= arr[1] ? 1 : 2;
		System.out.println(box());
	}
	
	static int box() {
		int ret = -1;
		
		for(int i=2; i<n; i++) {
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j])
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
			
			ret = Math.max(dp[i],ret);
		}
		
		return ret;
	}

}
