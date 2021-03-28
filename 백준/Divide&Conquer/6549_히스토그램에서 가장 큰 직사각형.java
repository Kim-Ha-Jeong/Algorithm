import java.io.*;

public class BOJ_6549 {
	static int h[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			String s[] = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);

			if (n == 0)
				break;
			h = new int[n];

			for (int i = 0; i < n; i++)
				h[i] = Integer.parseInt(s[i + 1]);

			bw.write(findMax(0, n - 1) + "\n");
		}

		bw.flush();
		bw.close();
	}

	static long findMax(int left, int right) {
		if (left == right)
			return h[left];

		int mid = (left + right) / 2;

		long result = (long) Math.max(findMax(left, mid), findMax(mid + 1, right));

		int low = mid, high = mid + 1;

		long height = (long) Math.min(h[low], h[high]);

		result = (long) Math.max(result, height * 2);

		while (left < low || high < right) {
			if (high < right && (left == low || h[low - 1] < h[high + 1])) {
				++high;
				height = (long) Math.min(height, h[high]);
			} else {
				--low;
				height = (long) Math.min(height, h[low]);
			}

			result = (long) Math.max(result, height * (high - low + 1));
		}

		return result;
	}
}
