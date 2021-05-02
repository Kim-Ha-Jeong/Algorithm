import java.io.*;

public class Greedy_1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		
		int k = Integer.parseInt(s[1]);
		int count = 0;
		
		int coin[] = new int[n];
		
		for(int i=0; i<n; i++) 
			coin[i] = Integer.parseInt(br.readLine().trim());
		
		for(int i=n-1; i>=0; i--) {
			if(k >= coin[i]) {
				count += (k/coin[i]);
				k %= coin[i];
			}
		}
		
		System.out.println(count);
		
	}

}
