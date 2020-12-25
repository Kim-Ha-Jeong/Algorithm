package study;

import java.util.*;

public class Divide {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = 1;
		int flag = 0;
		
		while(N > M) {
			
			int sum = 0;
			String str = Integer.toString(M);
			sum+=M;
			
			for(int i=0; i<str.length(); i++)
				sum += Character.getNumericValue(str.charAt(i));
			
			if(sum == N) {
				System.out.println(M);
				flag=1;
				break;
			}
	
			M++;
		}
		
		if(flag == 0)
			System.out.println(0);
		
	}
}
