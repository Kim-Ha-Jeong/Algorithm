import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class BOJ_9613 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);
			long sum = 0;
			
			long[] arr = new long[n];
			
			for(int i=0; i<n; i++) {
				arr[i] = Long.parseLong(s[i+1]);
			}
			
			Arrays.sort(arr);
			
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					sum += gcd(arr[i], arr[j]);
				}
			}
			
			sb.append(sum+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static long gcd(long a, long b) {
		while(a % b != 0) {
			long tmp = a % b;
			a = b;
			b = tmp;
		}
		
		return b;
	}

}
