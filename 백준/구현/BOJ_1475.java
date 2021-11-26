import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1475 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = br.readLine().trim();
		char[] num = n.toCharArray();
		int[] freq = new int[10];
		int count = 0;

		for (char a : num) {
			int partN = Character.getNumericValue(a);
			freq[partN]++;
		}

		int sum = freq[6] + freq[9];

		if (sum % 2 == 1) {
			count = Math.max(count, sum / 2 + 1);
		} else {
			count = Math.max(count, sum / 2);
		}

		for (int i = 0; i < freq.length; i++) {
			if (i != 6 && i != 9) {
				count = Math.max(count, freq[i]);
			}
		}

		System.out.println(count);
	}

}
