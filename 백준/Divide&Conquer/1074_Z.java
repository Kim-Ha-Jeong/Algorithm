import java.io.*;

public class BOJ_1074 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s[] = br.readLine().split(" ");

		int N = Integer.parseInt(s[0]);
		int n = (int) Math.pow(2, N);
		int r = Integer.parseInt(s[1]);
		int c = Integer.parseInt(s[2]);

		int row = 0, col = 0, cnt = 0;

		while (n > 0) {
			n /= 2;

			if (r < row + n && c < col + n)
				cnt += n * n * 0;
			else if (r < row + n) {
				cnt += n * n * 1;
				col += n;
			} else if (c < col + n) {
				cnt += n * n * 2;
				row += n;
			} else {
				cnt += n * n * 3;
				row += n;
				col += n;
			}

			if (n == 1) {
				bw.write(cnt + "");
				break;
			}
		}

		bw.flush();
		bw.close();

	}

}
