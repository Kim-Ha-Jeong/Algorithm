import java.io.*;
import java.util.Arrays;
public class Greedy_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		int w = -1;
		int arr[] = new int[k];
		
		for(int i=0; i<k; i++) 
			arr[i] = Integer.parseInt(br.readLine());		
		
		Arrays.sort(arr);
		
		for(int i=0; i<k; i++) 
			w = Math.max(w, (k-i)*arr[i]);
		
		System.out.println(w);
	}
}
