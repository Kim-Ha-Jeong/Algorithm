package study;

import java.util.*;

public class Leave {
	static int max = 0;
	static int n, t[], p[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int sum = 0;
		n = sc.nextInt();
		t = new int[n + 1];
		p = new int[n + 1];
		
		for(int i=1; i<=n; i++) {
			t[i] = sc.nextInt();
			p[i] = sc.nextInt();
		}

		dfs(1, 0);
		System.out.println(max);
	}

	static void dfs(int date, int P) {
		if (date == n + 1) {
			if (max < P)
				max = P;
			return;
		}

		if (date + t[date] - 1 <= n)
			dfs(date + t[date], P + p[date]);
		dfs(date + 1, P);

	}
}
