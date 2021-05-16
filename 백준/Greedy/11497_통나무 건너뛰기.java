import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Greedy_13 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int arr[] = new int[n];
			int wood[] = new int[n];
			
			String s[] = br.readLine().split(" ");
			
			for(int i=0; i<n; i++)
				arr[i] = Integer.parseInt(s[i]);
			
			Arrays.sort(arr);
			
			for(int i=0, j=0; j<n/2; i=i+2, j++) {
				wood[j] = arr[i];
				wood[n-j-1] = arr[i+1];
			}
			
			if(n % 2 == 1)
				wood[n/2] = arr[n-1];
			
			int max = Math.abs(wood[0]-wood[n-1]);
			for(int i=1; i<n; i++) 
				max = Math.max(max,Math.abs(wood[i]-wood[i-1]));
			
			System.out.println(max);
		}
	}

}
