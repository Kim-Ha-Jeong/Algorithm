import java.util.Scanner;
import java.io.*;

public class BOJ_2447 {
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();

		arr = new char[N][N];

		star(0, 0, N, false);

		for (int i = 0; i < N; i++) {
			bw.write(arr[i]);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		in.close();
	}

	static void star(int x, int y, int N, boolean blank) {

		if (blank) {
			for (int i = x; i < x + N; i++) {
				for (int j = y; j < y + N; j++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}

		if (N == 1) {
			arr[x][y] = '*';
			return;
		}

		int size = N / 3;
		int count = 0;
		for (int i = x; i < x + N; i += size) {
			for (int j = y; j < y + N; j += size) {
				count++;
				if (count == 5) {
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
}