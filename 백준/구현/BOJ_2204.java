import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2204 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr;

		while (true) {
			int n = Integer.parseInt(br.readLine());

			if (n == 0)
				break;

			arr = new String[n];

			for (int i = 0; i < n; i++) {
				arr[i] = br.readLine().trim();
			}

			Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
			System.out.println(arr[0]);
		}
	}

}
