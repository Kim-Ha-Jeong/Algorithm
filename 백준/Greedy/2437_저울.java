import java.io.*;
import java.util.Arrays;

public class Greedy_3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String s[] = br.readLine().split(" ");
		int arr[] = new int[n];
		
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(s[i]);
		
		Arrays.sort(arr);
		
		int min = 1;
		for(int i=0; i<n; i++) {
			if(min < arr[i])
				break;
			min += arr[i];
		}
		
		System.out.println(min);
	}

}
