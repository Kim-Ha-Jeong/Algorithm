import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BOJ_2002 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new LinkedHashMap<>();

		int count = 0;
		int n = Integer.parseInt(br.readLine());
		int[] out = new int[n];

		for (int i = 1; i <= n; i++) {
			map.put(br.readLine().trim(), i);
		}

		for (int i = 0; i < n; i++) {
			out[i] = map.get(br.readLine().trim());
		}

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (out[i] > out[j]) {
					count++;
					break;
				}
			}
		}

		System.out.println(count);
	}

}
