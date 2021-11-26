import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11656 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		String[] suffix = new String[s.length()];

		for (int i = 0; i < s.length(); i++) {
			suffix[i] = s.substring(i);
		}

		Arrays.sort(suffix);

		for (int i = 0; i < suffix.length; i++) {
			System.out.println(suffix[i]);
		}
	}

}
