import java.util.Arrays;
import java.util.Comparator;

public class Greedy_18 {
	public int solution(int[][] routes) {
		int answer = 1;

		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] r1, int[] r2) {
				return r1[1] - r2[1];
			}
		});

		int time = routes[0][1];
		for (int i = 1; i < routes.length; i++) {
			if (time < routes[i][0]) {
				answer++;
				time = routes[i][1];
			}
		}
		return answer;
	}

}
