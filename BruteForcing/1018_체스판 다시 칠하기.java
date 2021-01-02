package study;

import java.util.*;

public class Chess {

	static int min = 64;
	static int n, m;
	static char arr[][];
	static char cut[][] = new char[8][8];
	static String correct[] = { "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW",
			"WBWBWBWB" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < m; j++)
				arr[i][j] = str.charAt(j);
		}

		for (int i = 0; i < n - 7; i++) {
			for (int j = 0; j < m - 7; j++) {
				copyArr(i,j);
				repaint();
			}
		}

		System.out.println(min);

	}

	public static void copyArr(int a, int b) {
		int x = a, y = b;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (b == y+8)
					b = y;
				if (a == x+8)
					a = x;
				cut[i][j] = arr[a][b];
				b++;
			}
			a++;
		}
	}

	public static void repaint() {
		int count = 0;

		char str[][] = new char[8][8];
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++)
				str[i][j] = cut[i][j];
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cut[i][j] != correct[i].charAt(j))
					count++;
			}
		}

		if (count >= 32)
			count = 64 - count;

		if (min > count)
			min = count;
	}

}
