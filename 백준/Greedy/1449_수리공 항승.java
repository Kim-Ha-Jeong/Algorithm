import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Greedy_8 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");

		int N = Integer.parseInt(s[0]);
		int L = Integer.parseInt(s[1]);
		int arr[] = new int[N];

		s = br.readLine().split(" ");

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(s[i]);

		Arrays.sort(arr);
		
		int count = 1;
		int idx = 0;

		for (int j = idx + 1; j < N; j++) {
			if (arr[idx] + L - 1 < arr[j]) {
				count++;
				idx = j;
			}
		}

		System.out.println(count);
	}

}
