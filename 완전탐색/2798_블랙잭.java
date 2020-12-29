package study;

import java.util.*;

public class BlackJack {
	static int max = 0;
	static int n, m;
	static boolean visited[];
	static int card[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		card = new int[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++)
			card[i] = sc.nextInt();

		dfs(0, 0);
		System.out.println(max);
	}

	public static void dfs(int start, int index) {
		if (index == 3) {
			int sum = 0;
			for(int i=0; i<n; i++) {
				if(visited[i])
					sum+=card[i];
			}
			
			if(sum <= m && sum > max)
				max = sum;
		} else {

			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					visited[i] = true;
					dfs(i, index + 1);
					visited[i] = false;
				}
			}
		}
	}

}
