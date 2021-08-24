import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11399 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(s[i]);
		
		Arrays.sort(arr);
		
		int sum = 0;
		int ret = 0;
		
		for(int i=0; i<n; i++) {
			sum += arr[i];
			ret += sum;
		}
		
		System.out.println(ret);
	}

}
