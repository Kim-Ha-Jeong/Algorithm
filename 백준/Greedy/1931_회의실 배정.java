import java.io.*;
import java.util.*;
public class Greedy_4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int arr[][] = new int[n][2];
		
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(s[0]);
			arr[i][1] = Integer.parseInt(s[1]);
		}
		
		Arrays.sort(arr, new Comparator<int []>() {
			@Override
			public int compare(int[] a, int[] b) {
				if(a[1] == b[1])
					return a[0]-b[0];
				else
					return a[1]-b[1];
			}
		});
		
		int count = 0;
		int end = -1;
		
		for(int i=0; i<n; i++) {
			if(arr[i][0] >= end) {
				end = arr[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}

}
