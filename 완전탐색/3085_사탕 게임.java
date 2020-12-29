package study;

import java.util.Scanner;

public class Candy {
	static int max = 0;
	static char board[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		board = new char[n][n];

		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < n; j++)
				board[i][j] = str.charAt(j);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j != n - 1 && board[j] != board[j + 1]) {
					swapNext(i, j, j + 1);
					check(n);
					swapNext(i, j, j + 1);
				}

				if (i != n - 1 && board[i] != board[i + 1]) {
					swapDown(j, i, i + 1);
					check(n);
					swapDown(j, i, i + 1);
				}
			}
		}

		System.out.println(max);

	}

	public static void swapNext(int i, int a, int b) {
		char tmp;
		tmp = board[i][a];
		board[i][a] = board[i][b];
		board[i][b] = tmp;
	}

	public static void swapDown(int j, int a, int b) {
		char tmp;
		tmp = board[a][j];
		board[a][j] = board[b][j];
		board[b][j] = tmp;
	}

	public static void check(int n) {
		for (int i = 0; i < n; i++) {
			int sum = 1;

			for (int j = 1; j < n; j++) {
				if (board[i][j] == board[i][j - 1])
					sum++;
				else
					sum = 1;

				if (max < sum)
					max = sum;
			}
		}

		for (int i = 0; i < n; i++) {
			int sum = 1;
			for (int j = 1; j < n; j++) {
				if (board[j][i] == board[j - 1][i])
					sum++;
				else
					sum = 1;

				if (max < sum)
					max = sum;
			}
		}
	}

}
