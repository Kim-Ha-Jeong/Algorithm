import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class DP_15 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int length = str.length();
		long[] dp = new long[length + 1];
		int flag = 0;
		
		dp[0] = dp[1] = 1;

		if (str.charAt(0) == '0')
			flag = 1;
		else if (str.charAt(length - 1) == '0' && str.charAt(length - 2) != '1' && str.charAt(length - 2) != '2')
			flag = 1;
		else {
			for (int i = 2; i <= length; i++) {
				if (str.charAt(i-1) > '0')
					dp[i] = dp[i - 1] % 1000000;

				int tmp = Integer.parseInt(str.substring(i-2,i));
				if (10 <= tmp && tmp <= 26)
					dp[i] = (dp[i] + dp[i - 2]) % 1000000;
			}
		}
		
		if(flag != 0)
			bw.write("0");
		else
			bw.write(dp[length]+"");
		
		bw.flush();
		bw.close();
	}
}