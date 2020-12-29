package study;

import java.util.Scanner;

public class Teach {
	static boolean alpha[] = new boolean[26];
	static int max = 0;
	static String str[];
	static int K, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		if (K < 5)
			System.out.println(0);
		else if (K == 26)
			System.out.println(N);
		else {
			K -= 5;
			str = new String[N];

			alpha['a' - 97] = true;
			alpha['c' - 97] = true;
			alpha['i' - 97] = true;
			alpha['t' - 97] = true;
			alpha['n' - 97] = true;

			for (int i = 0; i < N; i++)
				str[i] = sc.next().replaceAll("anta|tica", "");

			dfs(0, 0);

			System.out.println(max);
		}
	}

	public static void dfs(int index, int start) {
		if (index == K) {
			int result = 0;
			for (int i = 0; i < N; i++) {
				int flag = 1;
				for (int j = 0; j < str[i].length(); j++) {
					if (!alpha[str[i].charAt(j) - 'a']) {
						flag = 0;
						break;
					}
				}

				if (flag == 1)
					result++;
			}

			if (max < result)
				max = result;

		} else {
			for (int i = start; i < 26; i++) {
				if (!alpha[i]) {
					alpha[i] = true;
					dfs(index + 1, i);
					alpha[i] = false;
				}
			}
		}
	}

}
