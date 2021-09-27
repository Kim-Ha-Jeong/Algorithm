import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1912 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n];
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		
		int ret = dp[0] = arr[0];
		
		for(int i=1; i<n; i++) {
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			ret = Math.max(dp[i], ret);
		}
		
		bw.write(ret+"");
		bw.flush();
		bw.close();
	}

}
