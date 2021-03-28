import java.io.*;

public class BOJ_2630 {
	static int arr[][], color = -1, white = 0, blue = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}

		divide(n, 0, 0);

		bw.write(white + "\n" + blue);
		bw.flush();
		bw.close();

	}

	static void divide(int n, int y, int x) {
		if (check(n, y, x)) {
			if (color == 1)
				blue++;
			else if (color == 0)
				white++;
		} else {
			n /= 2;

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++)
					divide(n, y + i * n, x + j * n);
			}
		}
	}

	static boolean check(int n, int y, int x) {
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (arr[y][x] != arr[i][j])
					return false;
			}
		}

		color = arr[y][x];
		return true;
	}
}
