package study;

import java.util.*;

public class Calculate {
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int n;
	static int arr[], op[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n];
		op = new int[4];


		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();

		for (int i = 0; i < 4; i++)
			op[i] = sc.nextInt();

		cal(arr[0], 1);
		
		System.out.println(max);
		System.out.println(min);
	}

	public static void cal(int result, int index) {

		if (index == n) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		for (int j = 0; j < 4; j++) {
			if (op[j] > 0) {
				
				op[j]--;
				
				if (j == 0)
					cal(result+arr[index], index+1);
				else if (j == 1)
					cal(result-arr[index], index+1);
				else if (j == 2)
					cal(result*arr[index], index+1);
				else
					cal(result/arr[index], index+1);
				
				op[j]++;
			}
		}

	}

}
