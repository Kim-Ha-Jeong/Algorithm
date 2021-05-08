import java.util.Arrays;

public class Greedy_6 {

	public int solution(int[] people, int limit) {
		int answer = 0;
		int small = 0;
		int large = people.length - 1;

		Arrays.sort(people);

		while (large > small) {
			if (people[small] + people[large] <= limit)
				small++;
			large--;
			answer++;
		}
		if (large == small)
			answer++;
		return answer;
	}

}
