package study;

import java.util.*;

public class Nqueen {
	static int n;
	static int used[];
	static int sum = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		used = new int[n];

		NQueen(0);
		System.out.println(sum);
	}

	static void NQueen(int index) {
		if(index == n) {
			sum++;
		} else {
			for(int i=0; i<n; i++) {
				used[index] = i;
				if(check(index))
					NQueen(index+1);
			}
		}
	}
	
	static boolean check(int row) {
		for(int i=0; i<row; i++) {
			if(used[row] == used[i])
				return false;
			else if(Math.abs(used[row]-used[i])==Math.abs(row - i))
				return false;
		}
		
		return true;
	}

}
