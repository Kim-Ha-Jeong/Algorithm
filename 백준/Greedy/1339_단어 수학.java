import java.io.*;
import java.util.Arrays;
import java.util.Collections;
public class BOJ_1339 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] alpha = new Integer[26];
		Arrays.fill(alpha, 0);
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			int length = str.length();
			
			for(int j=0; j<length; j++) {
				alpha[str.charAt(j)-65] += (int) Math.pow(10, length-j-1);
			}
		}
		
		Arrays.sort(alpha, Collections.reverseOrder());
		
		int sum = 0;
		int num = 9;
		for(int ele : alpha) {
			if(ele == 0) break;
			sum += (num*ele);
			num--;
		}
		
		System.out.println(sum);
	}

}
