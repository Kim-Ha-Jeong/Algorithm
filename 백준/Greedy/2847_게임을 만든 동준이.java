import java.io.*;

public class BOJ_2847 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int sum = 0;
		for(int i=n-1; i>=1; i--) {
			if(arr[i-1] >= arr[i]) {
				sum += arr[i-1]-arr[i]+1;
				arr[i-1] = arr[i]-1;
			}
		}
		
		System.out.println(sum);
	}

}
