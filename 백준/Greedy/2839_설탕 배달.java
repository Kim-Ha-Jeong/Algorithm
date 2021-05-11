import java.io.*;
public class Greedy_9 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int flag = 0;
		int share = n/5;
		int rest;
		
		for(int i=share; i>=0; i--) {
			rest = n - (5*i);
			if(rest%3 == 0) {
				flag = 1;
				share = i;
				break;
			}
		}
		
		share+=(n-share*5)/3;
		if(flag == 1)
			System.out.println(share);
		else
			System.out.println(-1);
		
	}

}
