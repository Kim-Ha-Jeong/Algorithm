package study;

import java.util.Scanner;

public class Add123 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];
		int recur[] = new int[12];

		recur[1] = 1;
		recur[2] = 2;
		recur[3] = 4;

		for (int i = 4; i <= 11; i++)
			recur[i] = recur[i - 3] + recur[i - 2] + recur[i - 1];

		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();

		for (int i = 0; i < n; i++)
			System.out.println(recur[arr[i]]);

	}
}
