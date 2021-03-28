package study;

import java.util.*;

public class Mass {

	static int arr[][];
	static int rank[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		arr = new int[n][2];
		rank = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			rank[i] = 1;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][0] < arr[j][0]) {
					if(arr[i][1] < arr[j][1])
						rank[i]++;
				}
			}
		}
		
		for(int i=0; i<n; i++)
			System.out.println(rank[i]);

	}

}
