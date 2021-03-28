import java.io.*;

public class BOJ_2331 {
	static int D[] = new int[10000];
	static int p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s[] = br.readLine().split(" ");
		D[0] = Integer.parseInt(s[0]);
		p = Integer.parseInt(s[1]);

		repeat(1);
	}

	static void repeat(int i) {
		int result = 0;
		String a = Integer.toString(D[i - 1]);

		for (int j = 0; j < a.length(); j++) {
			int part = a.charAt(j) - '0';
			D[i] += (int) Math.pow((double)part, (double)p);
		}

		result = check(i);
		if (result != -1) {
			System.out.println(result);
			return;
		}

		repeat(i + 1);
	}

	static int check(int i) {

		for (int j = 0; j < i; j++) {
			if (D[j] == D[i])
				return j;
		}

		return -1;
	}

}
