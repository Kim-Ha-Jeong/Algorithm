import java.io.*;

public class Greedy_5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int k = Integer.parseInt(br.readLine());
		
		System.out.println(solution(s, k));
	}

	static String solution(String number, int k) {
		StringBuilder answer = new StringBuilder();
		char max;
		int idx = 0;

		for (int i = 0; i < number.length() - k; i++) {
			max = '0';
			for (int j = idx; j <= k + i; j++) {
				if (number.charAt(j) > max) {
					max = number.charAt(j);
					idx = j + 1;
				}
			}
			answer.append(max);
		}

		return answer.toString();
	}

}
