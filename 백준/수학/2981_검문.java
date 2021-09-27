import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
public class BOJ_2981 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int ret = arr[1] - arr[0];
		
		for(int i=2; i<n; i++) {
			ret = gcd(ret, arr[i] - arr[i-1]);
		}
		
		for(int i=2; i<=ret; i++) {
			if(ret % i == 0)
				sb.append(i+" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int gcd(int a, int b) {
		while(a%b != 0) {
			int tmp = a%b;
			a = b;
			b = tmp;
		}
		
		return b;
	}
}
